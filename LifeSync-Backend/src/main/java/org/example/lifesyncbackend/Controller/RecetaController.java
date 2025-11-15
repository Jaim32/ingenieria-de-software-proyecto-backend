package org.example.lifesyncbackend.Controller;

import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.create.CreateRecetaDTO;
import org.example.lifesyncbackend.Domain.DTO.response.RecetaResponseDTO;
import org.example.lifesyncbackend.Service.iRecetaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/recetas")
@RequiredArgsConstructor
public class RecetaController {

    private final iRecetaService recetaService;

    @PostMapping
    public ResponseEntity<RecetaResponseDTO> createReceta(@RequestBody CreateRecetaDTO recetaDTO) throws Exception {
        return ResponseEntity.ok(recetaService.createReceta(recetaDTO));
    }

    @GetMapping
    public List<RecetaResponseDTO> getAllRecetas() {
        return recetaService.getAllRecetas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaResponseDTO> getRecetaById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(recetaService.getRecetaById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetaResponseDTO> updateReceta(@PathVariable Long id, @RequestBody CreateRecetaDTO recetaDTO) throws Exception {
        return ResponseEntity.ok(recetaService.updateReceta(id, recetaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RecetaResponseDTO> deleteReceta(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(recetaService.deleteReceta(id));
    }

    @PutMapping("/{id}/aprobacion")
    @PreAuthorize("hasRole('CATADOR')")
    public ResponseEntity<RecetaResponseDTO> aprobarReceta(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> body) throws Exception {
        Boolean aprobada = body.get("aprobada");
        return ResponseEntity.ok(recetaService.aprobarReceta(id, aprobada));
    }

    @GetMapping("/publicas")
    public List<RecetaResponseDTO> getRecetasPublicas() {
        return recetaService.getRecetasPublicas();
    }

    @GetMapping("/pendientes")
    @PreAuthorize("hasRole('CATADOR')")
    public List<RecetaResponseDTO> getRecetasNoAprobadas() {
        return recetaService.getRecetasNoAprobadas();
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<RecetaResponseDTO>> getRecetasByUsuario(@PathVariable UUID idUsuario) {
        return ResponseEntity.ok(recetaService.getRecetasByUsuarioId(idUsuario));
    }


}