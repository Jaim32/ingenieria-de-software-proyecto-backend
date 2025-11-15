package org.example.lifesyncbackend.Service.ServiceImplementation;

import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.create.CreatePlatilloDTO;
import org.example.lifesyncbackend.Domain.DTO.response.PlatilloResponseDTO;
import org.example.lifesyncbackend.Domain.Entity.Platillo;
import org.example.lifesyncbackend.Domain.Entity.Usuario;
import org.example.lifesyncbackend.Exceptions.ModelNotFoundException;
import org.example.lifesyncbackend.Repository.iPlatilloRepository;
import org.example.lifesyncbackend.Repository.iUsuarioRepository;
import org.example.lifesyncbackend.Service.iPlatilloService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlatilloServiceImpl implements iPlatilloService {

    private final iPlatilloRepository platilloRepository;
    private final iUsuarioRepository usuarioRepository;

    @Override
    public PlatilloResponseDTO createPlatillo(CreatePlatilloDTO dto) throws Exception {
        Usuario usuario = usuarioRepository.findByIdOrThrows(dto.getIdUsuario());

        Platillo platillo = new Platillo();
        platillo.setMeal(dto.getMeal());                         // NUEVO
        platillo.setProteina(dto.getProteina());
        platillo.setCarbohidrato(dto.getCarbohidrato());
        platillo.setVegetal(dto.getVegetal());
        platillo.setCaloriasTotales(dto.getCaloriasTotales());
        platillo.setFecha(dto.getFecha());
        platillo.setUsuario(usuario);

        Platillo saved = platilloRepository.save(platillo);
        return mapToPlatilloResponseDTO(saved);
    }

    @Override
    public PlatilloResponseDTO getPlatilloById(Long idPlatillo) throws Exception {
        Platillo platillo = platilloRepository.findById(idPlatillo)
                .orElseThrow(() -> new ModelNotFoundException("Platillo no encontrado con ID: " + idPlatillo));

        return mapToPlatilloResponseDTO(platillo);
    }

    @Override
    public PlatilloResponseDTO deletePlatillo(Long idPlatillo) throws Exception {
        Platillo platillo = platilloRepository.findById(idPlatillo)
                .orElseThrow(() -> new ModelNotFoundException("Platillo no encontrado con ID: " + idPlatillo));

        platilloRepository.delete(platillo);
        return mapToPlatilloResponseDTO(platillo);
    }

    @Override
    public PlatilloResponseDTO updatePlatillo(Long idPlatillo, CreatePlatilloDTO dto) throws Exception {
        Platillo platillo = platilloRepository.findById(idPlatillo)
                .orElseThrow(() -> new ModelNotFoundException("Platillo no encontrado con ID: " + idPlatillo));

        Usuario usuario = usuarioRepository.findByIdOrThrows(dto.getIdUsuario());

        platillo.setMeal(dto.getMeal());
        platillo.setProteina(dto.getProteina());
        platillo.setCarbohidrato(dto.getCarbohidrato());
        platillo.setVegetal(dto.getVegetal());
        platillo.setCaloriasTotales(dto.getCaloriasTotales());
        platillo.setFecha(dto.getFecha());
        platillo.setUsuario(usuario);

        Platillo updated = platilloRepository.save(platillo);
        return mapToPlatilloResponseDTO(updated);
    }

    @Override
    public List<PlatilloResponseDTO> getPlatillosByUsuarioId(UUID idUsuario) throws Exception {
        List<Platillo> platillos = platilloRepository.findByUsuario_IdUsuario(idUsuario);


        return platillos.stream()
                .map(this::mapToPlatilloResponseDTO)
                .toList(); // Java 16+
    }

    private PlatilloResponseDTO mapToPlatilloResponseDTO(Platillo p) {
        return new PlatilloResponseDTO(
                p.getMeal(),
                p.getProteina(),
                p.getCarbohidrato(),
                p.getVegetal(),
                p.getCaloriasTotales(),
                p.getUsuario() != null ? p.getUsuario().getIdUsuario() : null,
                p.getFecha()
        );
    }
}
