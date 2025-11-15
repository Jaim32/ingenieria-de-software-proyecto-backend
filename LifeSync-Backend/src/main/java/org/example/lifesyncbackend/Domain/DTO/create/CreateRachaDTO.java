package org.example.lifesyncbackend.Domain.DTO.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRachaDTO {
    private Integer puntos;
    private boolean privilegio;
    private UUID idUsuario;
    @NotNull
    private LocalDate fecha;

}
