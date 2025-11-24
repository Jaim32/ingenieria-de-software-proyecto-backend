package org.example.lifesyncbackend.Service;

import org.example.lifesyncbackend.Domain.DTO.create.ComentarioCreateDTO;
import org.example.lifesyncbackend.Domain.DTO.response.ComentarioResponseDTO;
import org.example.lifesyncbackend.Domain.DTO.update.ComentarioUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface iComentarioService {

    // --- Comentarios en Posts ---
    ComentarioResponseDTO createComentario(UUID postId, ComentarioCreateDTO comentarioDTO, UUID idUsuario);

    List<ComentarioResponseDTO> getComentariosByPost(UUID postId);

    // --- Comentarios en Recetas ---
    ComentarioResponseDTO createComentarioEnReceta(Long recetaId, ComentarioCreateDTO dto, UUID idUsuario);

    List<ComentarioResponseDTO> getComentariosByReceta(Long recetaId);

    // --- Update / Delete ---
    ComentarioResponseDTO updateComentario(UUID comentarioId, ComentarioUpdateDTO comentarioDTO, UUID idUsuario);

    void deleteComentario(UUID comentarioId, UUID idUsuario);

}
