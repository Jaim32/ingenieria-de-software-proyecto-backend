package org.example.lifesyncbackend.Domain.DTO.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;


@Data
public class CreateRecetaDTO {
    @NotBlank
    @NotEmpty
    @NotNull
    private String proteina;
    @NotBlank
    @NotEmpty
    @NotNull
    private String corteProteina;
    @NotBlank
    @NotEmpty
    @NotNull
    private String nombre;
    @NotBlank
    @NotEmpty
    @NotNull
    private String porcionProteina;
    @NotBlank
    @NotEmpty
    @NotNull
    private String ingredientesLista;
    @NotBlank
    @NotEmpty
    @NotNull
    private String coccionProteina;
    @NotBlank
    @NotEmpty
    @NotNull
    private String carbohidratos;
    @NotBlank
    @NotEmpty
    @NotNull
    private String porcionCarbohidratos;
    @NotBlank
    @NotEmpty
    @NotNull
    private String coccionCarbohidratos;
    @NotBlank
    @NotEmpty
    @NotNull
    private String vegetales;
    @NotBlank
    @NotEmpty
    @NotNull
    private String porcionVegetales;
    @NotBlank
    @NotEmpty
    @NotNull
    private String coccionVegetales;
    @NotBlank
    @NotEmpty
    @NotNull
    private String descripcion;
    @NotBlank
    @NotEmpty
    @NotNull
    private String procedimiento;
    @NotNull
    private LocalDate fecha;

    @NotBlank
    @NotEmpty
    @NotNull
    private String imagen;
    @NotBlank
    @NotEmpty
    @NotNull
    private String tipoDeComida;
    @NotNull
    private UUID idUsuario;
}
