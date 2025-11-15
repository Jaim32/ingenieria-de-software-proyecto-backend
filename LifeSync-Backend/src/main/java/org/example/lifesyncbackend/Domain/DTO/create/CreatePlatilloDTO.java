package org.example.lifesyncbackend.Domain.DTO.create;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlatilloDTO {
    @NotBlank
    @NotEmpty
    @NotNull
    private String meal;

    @NotBlank
    @NotEmpty
    @NotNull
    private String proteina;
    @NotBlank
    @NotEmpty
    @NotNull
    private String carbohidrato;
    @NotBlank
    @NotEmpty
    @NotNull
    private String vegetal;
    @NotBlank
    @NotEmpty
    @NotNull
    private int caloriasTotales;
    @NotNull
    private LocalDate fecha;

    @NotNull
    private UUID idUsuario;


}
