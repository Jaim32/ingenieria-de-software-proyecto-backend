package org.example.lifesyncbackend.Controller;

import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.create.CreatePlatilloDTO;
import org.example.lifesyncbackend.Domain.DTO.response.PlatilloResponseDTO;
import org.example.lifesyncbackend.Service.iPlatilloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/platillos")
@RequiredArgsConstructor
public class PlatilloController {

    private final iPlatilloService platilloService;

    @PostMapping
    public ResponseEntity<PlatilloResponseDTO> createPlatillo(@RequestBody CreatePlatilloDTO dto) throws Exception {
        return ResponseEntity.ok(platilloService.createPlatillo(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatilloResponseDTO> getPlatilloById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(platilloService.getPlatilloById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlatilloResponseDTO> deletePlatillo(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(platilloService.deletePlatillo(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlatilloResponseDTO> updatePlatillo(@PathVariable Long id, @RequestBody CreatePlatilloDTO dto) throws Exception {
        return ResponseEntity.ok(platilloService.updatePlatillo(id, dto));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<PlatilloResponseDTO>> getPlatillosByUsuarioId(@PathVariable UUID idUsuario) throws Exception {
        List<PlatilloResponseDTO> platillos = platilloService.getPlatillosByUsuarioId(idUsuario);
        return ResponseEntity.ok(platillos != null ? platillos : List.of()); // nunca null
    }


}