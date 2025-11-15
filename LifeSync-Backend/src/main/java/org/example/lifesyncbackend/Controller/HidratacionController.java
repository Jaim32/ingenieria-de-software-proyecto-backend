// HidratacionController.java
package org.example.lifesyncbackend.Controller;

import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.ProgresoDTO;
import org.example.lifesyncbackend.Domain.DTO.response.HidratacionResponseDTO;
import org.example.lifesyncbackend.Service.iHidratacionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/hidratacion")
@RequiredArgsConstructor
public class HidratacionController {

    private final iHidratacionService service;

    /** Obtener estado y progreso diario */
    @GetMapping("/me")
    public HidratacionResponseDTO getMe(@AuthenticationPrincipal UUID userId) throws Exception {
        return service.getHidratacionByUserId(userId);
    }

    /** Agregar ml al progreso diario */
    @PostMapping("/me/add")
    public HidratacionResponseDTO addMe(
            @AuthenticationPrincipal UUID userId,
            @RequestBody ProgresoDTO body
    ) throws Exception {
        return service.updateProgresoHidratacion(userId, body.getProgreso());
    }
}
