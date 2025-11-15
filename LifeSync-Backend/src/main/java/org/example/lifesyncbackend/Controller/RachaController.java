package org.example.lifesyncbackend.Controller;

import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.create.CreateRachaDTO;
import org.example.lifesyncbackend.Domain.DTO.response.RachaResponseDTO;
import org.example.lifesyncbackend.Service.iRachaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/rachas")
@RequiredArgsConstructor
public class RachaController {

    private final iRachaService rachaService;

    @PostMapping
    public ResponseEntity<RachaResponseDTO> create(@RequestBody CreateRachaDTO dto) throws Exception {
        return ResponseEntity.ok(rachaService.createRacha(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RachaResponseDTO> getById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(rachaService.getRachaById(id));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<RachaResponseDTO> getByUsuario(@PathVariable UUID idUsuario) throws Exception {
        return ResponseEntity.ok(rachaService.getRachaByUsuarioId(idUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RachaResponseDTO> update(@PathVariable Long id, @RequestBody CreateRachaDTO dto) throws Exception {
        return ResponseEntity.ok(rachaService.updateRacha(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RachaResponseDTO> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(rachaService.deleteRacha(id));
    }

    @PutMapping("/usuario/{idUsuario}")
    public ResponseEntity<RachaResponseDTO> updateByUsuarioId(@PathVariable UUID idUsuario, @RequestBody CreateRachaDTO dto) throws Exception {
        return ResponseEntity.ok(rachaService.updateByUsuarioId(idUsuario, dto));
    }

    @GetMapping("/check")
    @PreAuthorize("isAuthenticated()")
    public RachaResponseDTO check(Authentication auth) throws Exception {
        UUID uid = UUID.fromString(auth.getName());

        try {
            return rachaService.evaluarYPromover(uid);
        } catch (IllegalStateException e) {
            return rachaService.getRachaByUsuarioId(uid);
        }
    }

}
