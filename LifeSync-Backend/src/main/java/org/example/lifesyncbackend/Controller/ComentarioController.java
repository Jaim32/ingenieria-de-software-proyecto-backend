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
@RequestMapping("/api") // Prefijo general de APIs
@RequiredArgsConstructor
public class ComentarioController {

    private final iComentarioService comentarioService;

    // ===========================
    //   CREAR COMENTARIO
    // ===========================
    @PostMapping("/posts/{postId}/comentarios")
    public ResponseEntity<ComentarioResponseDTO> crearComentario(
            @PathVariable UUID postId,
            @Valid @RequestBody ComentarioCreateDTO comentarioDTO
    ) {
        UUID userId = getAuthenticatedUserId();
        ComentarioResponseDTO nuevoComentario =
                comentarioService.createComentario(postId, comentarioDTO, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoComentario);
    }

    // ===========================
    //   LISTAR COMENTARIOS DE UN POST
    // ===========================
    @GetMapping("/posts/{postId}/comentarios")
    public ResponseEntity<List<ComentarioResponseDTO>> listarComentarios(
            @PathVariable UUID postId
    ) {
        List<ComentarioResponseDTO> comentarios = comentarioService.getComentariosByPost(postId);
        return ResponseEntity.ok(comentarios);
    }

    // ===========================
    //   ACTUALIZAR COMENTARIO
    // ===========================
    @PutMapping("/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioResponseDTO> actualizarComentario(
            @PathVariable UUID comentarioId,
            @Valid @RequestBody ComentarioUpdateDTO comentarioDTO
    ) {
        UUID userId = getAuthenticatedUserId();
        ComentarioResponseDTO actualizado =
                comentarioService.updateComentario(comentarioId, comentarioDTO, userId);

        return ResponseEntity.ok(actualizado);
    }

    // ===========================
    //   ELIMINAR COMENTARIO
    // ===========================
    @DeleteMapping("/comentarios/{comentarioId}")
    public ResponseEntity<Void> eliminarComentario(
            @PathVariable UUID comentarioId
    ) {
        UUID userId = getAuthenticatedUserId();
        comentarioService.deleteComentario(comentarioId, userId);
        return ResponseEntity.noContent().build();
    }

    // ===========================
    //   OBTENER ID DEL USUARIO LOGUEADO
    // ===========================
    private UUID getAuthenticatedUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // El JWT debe tener el UUID en el `sub`
        return UUID.fromString(auth.getName());
    }
}
