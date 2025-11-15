package org.example.lifesyncbackend.Service;

import org.example.lifesyncbackend.Domain.DTO.create.CreateRecetaDTO;
import org.example.lifesyncbackend.Domain.DTO.response.RecetaResponseDTO;

import java.util.List;
import java.util.UUID;

public interface iRecetaService {
    RecetaResponseDTO createReceta(CreateRecetaDTO recetaDTO) throws Exception;

    RecetaResponseDTO getRecetaById(Long idReceta) throws Exception;

    RecetaResponseDTO deleteReceta(Long idReceta) throws Exception;

    RecetaResponseDTO updateReceta(Long idReceta, CreateRecetaDTO recetaDTO) throws Exception;

    List<RecetaResponseDTO> getAllRecetas();

    RecetaResponseDTO aprobarReceta(Long idReceta, boolean aprobada) throws Exception;

    List<RecetaResponseDTO> getRecetasPublicas();

    List<RecetaResponseDTO> getRecetasNoAprobadas();

    List<RecetaResponseDTO> getRecetasByUsuarioId(UUID idUsuario);

}
