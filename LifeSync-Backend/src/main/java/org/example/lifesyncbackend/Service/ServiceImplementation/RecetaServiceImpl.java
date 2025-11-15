package org.example.lifesyncbackend.Service.ServiceImplementation;

import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.create.CreateRecetaDTO;
import org.example.lifesyncbackend.Domain.DTO.response.RecetaResponseDTO;
import org.example.lifesyncbackend.Domain.Entity.Receta;
import org.example.lifesyncbackend.Domain.Entity.Usuario;
import org.example.lifesyncbackend.Repository.iRecetaRepository;
import org.example.lifesyncbackend.Repository.iUsuarioRepository;
import org.example.lifesyncbackend.Service.iRecetaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecetaServiceImpl implements iRecetaService {

    private final iRecetaRepository recetaRepository;
    private final iUsuarioRepository usuarioRepository;

    @Override
    public RecetaResponseDTO createReceta(CreateRecetaDTO dto) throws Exception {
        Receta receta = new Receta();
        receta.setNombre(dto.getNombre());
        receta.setProteina(dto.getProteina());
        receta.setCorteProteina(dto.getCorteProteina());
        receta.setPorcionProteina(dto.getPorcionProteina());
        receta.setCoccionProteina(dto.getCoccionProteina());

        receta.setCarbohidratos(dto.getCarbohidratos());
        receta.setPorcionCarbohidratos(dto.getPorcionCarbohidratos());
        receta.setCoccionCarbohidratos(dto.getCoccionCarbohidratos());

        receta.setIngredientesLista(dto.getIngredientesLista());

        receta.setVegetales(dto.getVegetales());
        receta.setPorcionVegetales(dto.getPorcionVegetales());
        receta.setCoccionVegetales(dto.getCoccionVegetales());
        receta.setFecha(dto.getFecha());

        receta.setDescripcion(dto.getDescripcion());
        receta.setProcedimiento(dto.getProcedimiento());
        receta.setImagen(dto.getImagen());
        receta.setTipoDeComida(dto.getTipoDeComida());

        // Relación con Usuario
        Usuario usuario = usuarioRepository.findByIdUsuario(dto.getIdUsuario());
        if (usuario == null) throw new Exception("Usuario no encontrado");
        receta.setUsuario(usuario);

        recetaRepository.save(receta);
        return mapToRecetaResponseDTO(receta);
    }

    @Override
    public RecetaResponseDTO getRecetaById(Long id) throws Exception {
        Receta receta = recetaRepository.findByIdOrThrows(id);
        return mapToRecetaResponseDTO(receta);
    }

    @Override
    public RecetaResponseDTO deleteReceta(Long id) throws Exception {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new Exception("Receta no encontrada"));
        recetaRepository.delete(receta);
        return mapToRecetaResponseDTO(receta);
    }

    @Override
    public RecetaResponseDTO updateReceta(Long id, CreateRecetaDTO dto) throws Exception {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new Exception("Receta no encontrada"));

        receta.setNombre(dto.getNombre());
        receta.setProteina(dto.getProteina());
        receta.setCorteProteina(dto.getCorteProteina());
        receta.setPorcionProteina(dto.getPorcionProteina());
        receta.setCoccionProteina(dto.getCoccionProteina());

        receta.setCarbohidratos(dto.getCarbohidratos());
        receta.setPorcionCarbohidratos(dto.getPorcionCarbohidratos());
        receta.setCoccionCarbohidratos(dto.getCoccionCarbohidratos());

        receta.setIngredientesLista(dto.getIngredientesLista());

        receta.setVegetales(dto.getVegetales());
        receta.setPorcionVegetales(dto.getPorcionVegetales());
        receta.setCoccionVegetales(dto.getCoccionVegetales());
        receta.setFecha(dto.getFecha());

        receta.setDescripcion(dto.getDescripcion());
        receta.setProcedimiento(dto.getProcedimiento());
        receta.setImagen(dto.getImagen());
        receta.setTipoDeComida(dto.getTipoDeComida());

        recetaRepository.save(receta);
        return mapToRecetaResponseDTO(receta);
    }

    @Override
    public List<RecetaResponseDTO> getAllRecetas() {
        return recetaRepository.findAll()
                .stream()
                .map(this::mapToRecetaResponseDTO)
                .toList();
    }

    @Override
    public RecetaResponseDTO aprobarReceta(Long id, boolean aprobada) throws Exception {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new Exception("Receta no encontrada"));
        receta.setAprobada(aprobada);
        recetaRepository.save(receta);
        return mapToRecetaResponseDTO(receta);
    }

    @Override
    public List<RecetaResponseDTO> getRecetasPublicas() {
        return recetaRepository.findByAprobadaTrue()
                .stream()
                .map(this::mapToRecetaResponseDTO)
                .toList();
    }

    @Override
    public List<RecetaResponseDTO> getRecetasNoAprobadas() {
        return recetaRepository.findByAprobadaFalse()
                .stream()
                .map(this::mapToRecetaResponseDTO)
                .toList();
    }

    @Override
    public List<RecetaResponseDTO> getRecetasByUsuarioId(UUID idUsuario) {
        Usuario usuario = usuarioRepository.findByIdUsuario(idUsuario);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado con ID: " + idUsuario);
        }
        return recetaRepository.findAllByUsuario(usuario)
                .stream()
                .map(this::mapToRecetaResponseDTO)
                .toList();
    }

    private RecetaResponseDTO mapToRecetaResponseDTO(Receta r) {
        RecetaResponseDTO dto = new RecetaResponseDTO();

        dto.setIdReceta(r.getIdReceta());
        dto.setNombre(r.getNombre());

        dto.setProteina(r.getProteina());
        dto.setCorteProteina(r.getCorteProteina());
        dto.setPorcionProteina(r.getPorcionProteina());
        dto.setCoccionProteina(r.getCoccionProteina());

        dto.setCarbohidratos(r.getCarbohidratos());
        dto.setPorcionCarbohidratos(r.getPorcionCarbohidratos());
        dto.setCoccionCarbohidratos(r.getCoccionCarbohidratos());

        dto.setIngredientesLista(r.getIngredientesLista());

        dto.setVegetales(r.getVegetales());
        dto.setPorcionVegetales(r.getPorcionVegetales());
        dto.setCoccionVegetales(r.getCoccionVegetales());

        dto.setDescripcion(r.getDescripcion());
        dto.setProcedimiento(r.getProcedimiento());
        dto.setImagen(r.getImagen());
        dto.setTipoDeComida(r.getTipoDeComida());
        dto.setFecha(r.getFecha());

        dto.setAprobada(r.isAprobada());
        dto.setIdUsuario(r.getUsuario() != null ? r.getUsuario().getIdUsuario() : null);

        return dto;
    }
}