package org.example.lifesyncbackend.Domain.DTO.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateHidratacionDTO {
    @NotNull
    private boolean estado;

    @NotNull
    private double progreso;

    @NotNull
    private UUID idUsuario;

    @NotNull
    private LocalDate fecha;


}

