package org.example.lifesyncbackend.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.create.ComentarioCreateDTO;
import org.example.lifesyncbackend.Domain.DTO.response.ComentarioResponseDTO;
import org.example.lifesyncbackend.Domain.DTO.update.ComentarioUpdateDTO;
import org.example.lifesyncbackend.Service.iComentarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comment") // Prefijo base para tu API
@RequiredArgsConstructor
public class ComentarioController {

    private final iComentarioService comentarioService;


    @PostMapping("/posts/{postId}/comentarios")
    public ResponseEntity<ComentarioResponseDTO> crearComentario(
            @PathVariable UUID postId,
            @Valid @RequestBody ComentarioCreateDTO comentarioDTO
    ) {
        UUID userId = getAuthenticatedUserId(); // Obtenemos el ID del usuario logueado
        ComentarioResponseDTO nuevoComentario = comentarioService.createComentario(postId, comentarioDTO, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoComentario);
    }

    @GetMapping("/posts/{postId}/comentarios")
    public ResponseEntity<List<ComentarioResponseDTO>> listarComentarios(@PathVariable UUID postId) {
        List<ComentarioResponseDTO> comentarios = comentarioService.getComentariosByPost(postId);
        return ResponseEntity.ok(comentarios);
    }

    /**
     * ACTUALIZAR UN COMENTARIO
     * PUT /api/v1/comentarios/{comentarioId}
     */
    @PutMapping("/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioResponseDTO> actualizarComentario(
            @PathVariable UUID comentarioId,
            @Valid @RequestBody ComentarioUpdateDTO comentarioDTO
    ) {
        UUID userId = getAuthenticatedUserId();
        ComentarioResponseDTO actualizado = comentarioService.updateComentario(comentarioId, comentarioDTO, userId);
        return ResponseEntity.ok(actualizado);
    }

    /**
     * ELIMINAR UN COMENTARIO
     * DELETE /api/v1/comentarios/{comentarioId}
     */
    @DeleteMapping("/comentarios/{comentarioId}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable UUID comentarioId) {
        UUID userId = getAuthenticatedUserId();
        comentarioService.deleteComentario(comentarioId, userId);
        return ResponseEntity.noContent().build();
    }

    // --- MÉTODO PRIVADO PARA OBTENER EL ID DEL USUARIO ---
    private UUID getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // IMPORTANTE: Dependiendo de cómo configuraste tu 'UserDetails' o tu Token JWT,
        // el ID puede estar en diferentes lugares.

        // OPCIÓN A: Si tu principal es un objeto personalizado (ej. CustomUserDetails)
        // CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        // return userDetails.getId();

        // OPCIÓN B: Si el 'name' de la autenticación es el ID (UUID en String)
        return UUID.fromString(authentication.getName());

        // Si ninguna de estas te funciona, dime cómo es tu clase 'Usuario' o 'SecurityConfig'
        // para darte la línea exacta aquí.
    }
}