// src/main/java/org/example/lifesyncbackend/Controller/DailySnapshotController.java
package org.example.lifesyncbackend.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.DailySnapshotDTO;
import org.example.lifesyncbackend.Domain.DTO.response.*;
import org.example.lifesyncbackend.Service.ServiceImplementation.HidratacionServiceImpl;
import org.example.lifesyncbackend.Service.ServiceImplementation.PlatilloServiceImpl;
import org.example.lifesyncbackend.Service.iDailyDataArchiveService;
import org.example.lifesyncbackend.Service.iHidratacionService;
import org.example.lifesyncbackend.Service.iPlatilloService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/daily")
@RequiredArgsConstructor
public class DailySnapshotController {

    private final iDailyDataArchiveService archiveService;
    private final iHidratacionService      hidrService;
    private final iPlatilloService          platService;
    private final PlatilloServiceImpl platilloServiceImpl;
    private final HidratacionServiceImpl hidratacionServiceImpl;

    @GetMapping("/snapshot")
    public ResponseEntity<DailySnapshotDTO> getDailySnapshot(
            @RequestParam UUID usuarioId,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fecha
    ) throws Exception {

        LocalDate hoy = LocalDate.now();

        // si no vienen fecha o es hoy → datos “en vivo”
        if (fecha == null || fecha.equals(hoy)) {
            DailySnapshotDTO dto = new DailySnapshotDTO();
            dto.setIdUsuario(usuarioId);
            dto.setFecha(hoy);
            // hidratación “en vivo”
            dto.setHidratacion(hidratacionServiceImpl.getHidratacionByUserId(usuarioId));
            // platillos “en vivo”
            try {
                List<PlatilloResponseDTO> p = platilloServiceImpl.getPlatillosByUsuarioId(usuarioId);
                dto.setPlatillos(p);
            } catch (EntityNotFoundException e) {
                dto.setPlatillos(Collections.emptyList());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return ResponseEntity.ok(dto);
        }

        // si la fecha es anterior → leemos el JSON archivado
        try {
            DailyDataArchiveResponseDTO archived = archiveService.getArchive(usuarioId, fecha);
            // mapeamos a nuestro SnapshotDTO pero solo hidratación+platillos
            DailySnapshotDTO dto = new DailySnapshotDTO();
            dto.setIdUsuario(archived.getIdUsuario());
            dto.setFecha(archived.getFecha());
            dto.setHidratacion(archived.getHidratacion());
            dto.setPlatillos(archived.getPlatillos());
            return ResponseEntity.ok(dto);

        } catch (EntityNotFoundException ex) {
            // no hay archive para esa fecha → devolvemos vacío
            DailySnapshotDTO dto = new DailySnapshotDTO();
            dto.setIdUsuario(usuarioId);
            dto.setFecha(fecha);
            dto.setHidratacion(null);
            dto.setPlatillos(Collections.emptyList());
            return ResponseEntity.ok(dto);
        }
    }
}
