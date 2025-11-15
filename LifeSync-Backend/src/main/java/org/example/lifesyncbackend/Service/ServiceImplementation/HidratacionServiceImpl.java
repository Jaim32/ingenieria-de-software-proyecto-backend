// HidratacionServiceImpl.java
package org.example.lifesyncbackend.Service.ServiceImplementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.response.HidratacionResponseDTO;
import org.example.lifesyncbackend.Domain.Entity.Hidratacion;
import org.example.lifesyncbackend.Domain.Entity.Usuario;
import org.example.lifesyncbackend.Exceptions.ModelNotFoundException;
import org.example.lifesyncbackend.Repository.iHidratacionRepository;
import org.example.lifesyncbackend.Repository.iUsuarioRepository;
import org.example.lifesyncbackend.Service.iHidratacionService;
import org.example.lifesyncbackend.Util.HidroCalc;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HidratacionServiceImpl implements iHidratacionService {

    private final HidroCalc calc;
    private final iHidratacionRepository repo;
    private final iUsuarioRepository usuarios;

    @Override
    public HidratacionResponseDTO getHidratacionByUserId(UUID idUsuario) throws Exception {
        Usuario user = usuarios.findByIdOrThrows(idUsuario);
        LocalDate hoy = LocalDate.now();

        Hidratacion h = repo.findByUsuarioAndFecha(user, hoy)
                .orElseGet(() -> {
                    Hidratacion nuevo = new Hidratacion();
                    nuevo.setUsuario(user);
                    nuevo.setProgreso(0);
                    nuevo.setEstado(false);
                    nuevo.setFecha(hoy);
                    return repo.save(nuevo);
                });

        return map(h);
    }

    @Transactional
    @Override
    public HidratacionResponseDTO updateProgresoHidratacion(UUID idUsuario, double ml) throws Exception {
        Usuario user = usuarios.findByIdOrThrows(idUsuario);
        LocalDate hoy = LocalDate.now();

        Hidratacion h = repo.findByUsuarioAndFecha(user, hoy)
                .orElseThrow(() -> new ModelNotFoundException("No hay registro de hidratación para hoy."));

        h.setProgreso(h.getProgreso() + ml);
        double meta = calc.metaMl(user.getPeso());
        double pct  = (h.getProgreso() / meta) * 100.0;

        // Limitar el porcentaje al 100% y marcar completado cuando alcanza o supera el 100%
        h.setEstado(pct >= 100.0);

        Hidratacion saved = repo.save(h);
        return map(saved);
    }

    private HidratacionResponseDTO map(Hidratacion h) {
        double meta = calc.metaMl(h.getUsuario().getPeso());
        double pct  = (h.getProgreso() / meta) * 100.0;
        // porcentaje máximo 100
        double porcentajeFinal = Math.min(pct, 100.0);
        boolean completado     = pct >= 100.0;

        return new HidratacionResponseDTO(
                h.getUsuario().getIdUsuario(),
                h.getFecha(),
                h.getProgreso(),
                meta,
                porcentajeFinal,
                completado
        );
    }
}
