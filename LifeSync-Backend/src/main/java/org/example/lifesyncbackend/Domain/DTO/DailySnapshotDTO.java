package org.example.lifesyncbackend.Domain.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.response.HidratacionResponseDTO;
import org.example.lifesyncbackend.Domain.DTO.response.PlatilloResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailySnapshotDTO {
    private UUID idUsuario;
    private LocalDate fecha;

    // Solo hidratación:
    private HidratacionResponseDTO hidratacion;

    // Solo platillos:
    private List<PlatilloResponseDTO> platillos;

    // … puedes añadir aquí otros bloques (rachas, recetas) si quieres
    // getters + setters
}
