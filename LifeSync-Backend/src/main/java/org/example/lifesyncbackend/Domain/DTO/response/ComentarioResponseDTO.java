package org.example.lifesyncbackend.Domain.DTO.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ComentarioResponseDTO {

    private UUID idComentario;
    private String contenido;
    private LocalDateTime createdAt;

    // IDs dependiendo del tipo de comentario
    private UUID postId;     // null si el comentario pertenece a una receta
    private Long recetaId;   // null si el comentario pertenece a un post

    // Información del autor
    private UUID idUser;
    private String nombreUsuario;
}
