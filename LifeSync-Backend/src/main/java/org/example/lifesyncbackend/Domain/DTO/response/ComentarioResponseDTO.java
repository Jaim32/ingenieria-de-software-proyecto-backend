package org.example.lifesyncbackend.Domain.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioResponseDTO {

    private UUID idComentario;
    private String contenido;
    private LocalDateTime createdAt;
    // ID del post al que pertenece (útil para el frontend)
    private UUID postId;

    private UUID idUser;

}