package org.example.lifesyncbackend.Domain.DTO; // En la carpeta raíz de DTO

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO General "para todo".
 * ⚠️ NO SE RECOMIENDA: Mezcla campos de entrada (create) y salida (response).
 */
@Data
public class ComentarioDTO {

    // --- Campos de RESPUESTA (Salida) ---
    // El cliente no debería enviar esto al crear.
    private UUID idComentario;
    private LocalDateTime createdAt;

    // --- Campos de ENTRADA y RESPUESTA (Mixtos) ---
    private String contenido;


    // --- Campos AMBIGUOS (Entrada o Salida?) ---
    // ¿Es el ID del post al que pertenece (Salida)?
    // ¿O es el ID que el cliente envía al crear (Entrada)?
    // Es mejor que el ID del post venga en la URL.
    private UUID postId;

    // ¿Es el ID del autor al crear (Inseguro!)?
    // ¿O el ID del autor en la respuesta?
    // Es mejor que el ID del autor venga del token.
    private UUID usuarioId;
}