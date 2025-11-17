package org.example.lifesyncbackend.Service;

import org.example.lifesyncbackend.Domain.DTO.create.ComentarioCreateDTO;
import org.example.lifesyncbackend.Domain.DTO.response.ComentarioResponseDTO;
import org.example.lifesyncbackend.Domain.DTO.update.ComentarioUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface iComentarioService {

    /**
     * Crea un nuevo comentario para un post específico.
     * @param postId El ID del post al que se añade el comentario.
     * @param comentarioDTO El DTO con el contenido del comentario.
     * @param idUsuario El ID del usuario autenticado que está creando el comentario.
     * @return El DTO del comentario recién creado.
     */
    ComentarioResponseDTO createComentario(UUID postId, ComentarioCreateDTO comentarioDTO, UUID idUsuario);

    /**
     * Obtiene todos los comentarios de un post específico.
     * @param postId El ID del post.
     * @return Una lista de DTOs de los comentarios.
     */
    List<ComentarioResponseDTO> getComentariosByPost(UUID postId);

    /**
     * Actualiza un comentario existente.
     * @param comentarioId El ID del comentario a actualizar.
     * @param comentarioDTO El DTO con el nuevo contenido.
     * @param idUsuario El ID del usuario que intenta actualizar (para verificar permisos).
     * @return El DTO del comentario actualizado.
     */
    ComentarioResponseDTO updateComentario(UUID comentarioId, ComentarioUpdateDTO comentarioDTO, UUID idUsuario);

    /**
     * Elimina un comentario.
     * @param comentarioId El ID del comentario a eliminar.
     * @param idUsuario El ID del usuario que intenta eliminar (para verificar permisos).
     */
    void deleteComentario(UUID comentarioId, UUID idUsuario);

}