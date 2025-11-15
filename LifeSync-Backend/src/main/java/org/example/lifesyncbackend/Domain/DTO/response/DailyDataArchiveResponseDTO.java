package org.example.lifesyncbackend.Domain.DTO.response;

// DailyDataResponseDTO.java


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyDataArchiveResponseDTO {

    @JsonProperty("idUsuario")
    private UUID idUsuario;

    @JsonProperty("nombreUsuario")
    private String nombreUsuario;

    @JsonProperty("fecha")
    private LocalDate fecha;

    @JsonProperty("hidratacion")
    private HidratacionResponseDTO hidratacion;

    @JsonProperty("platillos")
    private List<PlatilloResponseDTO> platillos;

    @JsonProperty("racha")
    private RachaResponseDTO racha;

    @JsonProperty("recetas")
    private List<RecetaResponseDTO> recetas;

    // Si tenés otros response DTOs, agrégalos aquí
}
