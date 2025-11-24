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
@RequestMapping("/api")
@RequiredArgsConstructor
public class ComentarioController {

    private final iComentarioService comentarioService;

    // =====================================================
    // 🚀 1. CREAR COMENTARIO EN UN POST
    // =====================================================
    @PostMapping("/posts/{postId}/comentarios")
    public ResponseEntity<ComentarioResponseDTO> crearComentarioEnPost(
            @PathVariable UUID postId,
            @Valid @RequestBody ComentarioCreateDTO comentarioDTO
    ) {
        UUID userId = getAuthenticatedUserId();
        ComentarioResponseDTO nuevo =
                comentarioService.createComentario(postId, comentarioDTO, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // =====================================================
    // 📌 2. LISTAR COMENTARIOS DE UN POST
    // =====================================================
    @GetMapping("/posts/{postId}/comentarios")
    public ResponseEntity<List<ComentarioResponseDTO>> listarComentariosPost(
            @PathVariable UUID postId
    ) {
        return ResponseEntity.ok(
                comentarioService.getComentariosByPost(postId)
        );
    }

    // =====================================================
    // 🚀 3. CREAR COMENTARIO EN UNA RECETA (NUEVO)
    // =====================================================
    @PostMapping("/recetas/{recetaId}/comentarios")
    public ResponseEntity<ComentarioResponseDTO> crearComentarioEnReceta(
            @PathVariable Long recetaId,
            @Valid @RequestBody ComentarioCreateDTO comentarioDTO
    ) {
        UUID userId = getAuthenticatedUserId();

        ComentarioResponseDTO nuevo =
                comentarioService.createComentarioEnReceta(recetaId, comentarioDTO, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // =====================================================
    // 📌 4. LISTAR COMENTARIOS DE UNA RECETA (NUEVO)
    // =====================================================
    @GetMapping("/recetas/{recetaId}/comentarios")
    public ResponseEntity<List<ComentarioResponseDTO>> listarComentariosReceta(
            @PathVariable Long recetaId
    ) {
        return ResponseEntity.ok(
                comentarioService.getComentariosByReceta(recetaId)
        );
    }

    // =====================================================
    // ✏️ 5. EDITAR COMENTARIO
    // =====================================================
    @PutMapping("/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioResponseDTO> actualizarComentario(
            @PathVariable UUID comentarioId,
            @Valid @RequestBody ComentarioUpdateDTO comentarioDTO
    ) {
        UUID userId = getAuthenticatedUserId();

        return ResponseEntity.ok(
                comentarioService.updateComentario(comentarioId, comentarioDTO, userId)
        );
    }

    // =====================================================
    // 🗑️ 6. ELIMINAR COMENTARIO
    // =====================================================
    @DeleteMapping("/comentarios/{comentarioId}")
    public ResponseEntity<Void> eliminarComentario(
            @PathVariable UUID comentarioId
    ) {
        UUID userId = getAuthenticatedUserId();
        comentarioService.deleteComentario(comentarioId, userId);

        return ResponseEntity.noContent().build();
    }

    // =====================================================
    // 🔐 OBTENER ID DEL USUARIO DESDE JWT
    // =====================================================
    private UUID getAuthenticatedUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return UUID.fromString(auth.getName()); // en el JWT el sub = UUID del usuario
    }
}
