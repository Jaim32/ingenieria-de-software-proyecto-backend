package org.example.lifesyncbackend.Domain.DTO.create;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.HidratacionDTO;
import org.example.lifesyncbackend.Domain.DTO.PlatilloDTO;
import org.example.lifesyncbackend.Domain.DTO.RachaDTO;
import org.example.lifesyncbackend.Domain.DTO.RecetaDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDailyDataArchiveDTO {

    @NotNull
    private UUID usuarioId;

    @NotNull
    private LocalDate fecha;

    @NotNull
    private HidratacionDTO hidratacion;

    @NotNull
    private RachaDTO racha;

    @NotNull
    private List<PlatilloDTO> platillos;

    @NotNull
    private List<RecetaDTO> recetas;
}
