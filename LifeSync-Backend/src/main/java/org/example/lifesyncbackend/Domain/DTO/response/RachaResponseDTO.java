package org.example.lifesyncbackend.Domain.DTO.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RachaResponseDTO {
    private long idRacha;
    private Integer puntos;
    private boolean privilegio;
    private UUID idUsuario;
    @JsonProperty("fecha")
    private LocalDate fecha;
}