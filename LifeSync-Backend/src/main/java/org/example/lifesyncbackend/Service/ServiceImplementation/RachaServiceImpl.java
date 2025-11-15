package org.example.lifesyncbackend.Service.ServiceImplementation;

import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.create.CreateRachaDTO;
import org.example.lifesyncbackend.Domain.DTO.response.RachaResponseDTO;
import org.example.lifesyncbackend.Domain.Entity.Racha;
import org.example.lifesyncbackend.Domain.Entity.Rol;
import org.example.lifesyncbackend.Domain.Entity.Usuario;
import org.example.lifesyncbackend.Exceptions.ModelNotFoundException;
import org.example.lifesyncbackend.Repository.iRachaRepository;
import org.example.lifesyncbackend.Repository.iUsuarioRepository;
import org.example.lifesyncbackend.Service.iRachaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class RachaServiceImpl implements iRachaService {



    private static final int PUNTOS_UMBRAL = 750;
    private static final ZoneId SV_ZONE = ZoneId.of("America/El_Salvador");
    private static final int PUNTOS_POR_DIA = 10;


    private final iRachaRepository rachaRepository;
    private final iUsuarioRepository usuarioRepository;

    @Override
    public RachaResponseDTO createRacha(CreateRachaDTO dto) throws Exception {
        Usuario usuario = usuarioRepository.findByIdOrThrows(dto.getIdUsuario());
        Racha racha = new Racha();
        racha.setPuntos(dto.getPuntos());
        racha.setPrivilegio(dto.isPrivilegio());
        racha.setUsuario(usuario);
        racha.setFecha(dto.getFecha());
        Racha saved = rachaRepository.save(racha);
        return mapToResponse(saved);
    }

    @Override
    public RachaResponseDTO getRachaById(Long id) throws Exception {
        Racha racha = rachaRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Racha no encontrada con ID: " + id));
        return mapToResponse(racha);
    }

    @Override
    public RachaResponseDTO getRachaByUsuarioId(UUID idUsuario) throws Exception {
        Racha racha = rachaRepository.findByUsuario_IdUsuario(idUsuario)
                .orElseThrow(() -> new ModelNotFoundException("Racha no encontrada para el usuario con ID: " + idUsuario));
        return mapToResponse(racha);
    }

    @Override
    public RachaResponseDTO updateRacha(Long id, CreateRachaDTO dto) throws Exception {
        Racha racha = rachaRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Racha no encontrada con ID: " + id));
        Usuario usuario = usuarioRepository.findByIdOrThrows(dto.getIdUsuario());
        racha.setPuntos(dto.getPuntos());
        racha.setPrivilegio(dto.isPrivilegio());
        racha.setUsuario(usuario);
        Racha updated = rachaRepository.save(racha);
        return mapToResponse(updated);
    }

    @Override
    public RachaResponseDTO deleteRacha(Long id) throws Exception {
        Racha racha = rachaRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Racha no encontrada con ID: " + id));
        rachaRepository.delete(racha);
        return mapToResponse(racha);
    }

    @Override
    public RachaResponseDTO updateByUsuarioId(UUID idUsuario, CreateRachaDTO dto) throws Exception {
        Racha racha = rachaRepository.findByUsuario_IdUsuario(idUsuario)
                .orElseThrow(() -> new ModelNotFoundException("Racha no encontrada para el usuario con ID: " + idUsuario));
        Usuario usuario = usuarioRepository.findByIdOrThrows(dto.getIdUsuario());
        racha.setPuntos(dto.getPuntos());
        racha.setPrivilegio(dto.isPrivilegio());
        racha.setUsuario(usuario);
        Racha updated = rachaRepository.save(racha);
        return mapToResponse(updated);
    }


    @Transactional
    @Override
    public RachaResponseDTO evaluarYPromover(UUID idUsuario) throws Exception {

        Usuario usuario = usuarioRepository.findByIdOrThrows(idUsuario);

        Racha racha = rachaRepository.findByUsuario_IdUsuario(idUsuario)
                .orElseThrow(() -> new ModelNotFoundException(
                        "Racha no encontrada para el usuario con ID: " + idUsuario));

        if (racha.getPuntos() < PUNTOS_UMBRAL) {            // < 750
            throw new IllegalStateException(
                    "Se requieren %d puntos; el usuario sólo tiene %d"
                            .formatted(PUNTOS_UMBRAL, racha.getPuntos()));
        }

        // ——— promoción ―—————————————————————————
        if (usuario.getRol() != Rol.PREMIUM) {
            usuario.promoverAPremium();     // cambia rol
            racha.setPrivilegio(true);      // activa flag
        }

        return mapToResponse(racha);        // DTO refleja privilegio=true
    }



    private RachaResponseDTO mapToResponse(Racha r) {
        return new RachaResponseDTO(
                r.getIdRacha(),
                r.getPuntos(),
                r.isPrivilegio(),
                r.getUsuario() != null ? r.getUsuario().getIdUsuario() : null,
                r.getFecha()
        );
    }


    @Override
    public void registrarInicioSesion(UUID idUsuario) throws Exception {

        Racha racha = rachaRepository.findByUsuario_IdUsuario(idUsuario)
                .orElseThrow(() -> new ModelNotFoundException(
                        "Racha no encontrada para el usuario con ID: " + idUsuario));

        LocalDate hoy = LocalDate.now(SV_ZONE);

        if (racha.getLastStreakDate() == null || !racha.getLastStreakDate().isEqual(hoy)) {
            racha.setPuntos(racha.getPuntos() + PUNTOS_POR_DIA);
            racha.setLastStreakDate(hoy);
            rachaRepository.save(racha);
        }
    }

}