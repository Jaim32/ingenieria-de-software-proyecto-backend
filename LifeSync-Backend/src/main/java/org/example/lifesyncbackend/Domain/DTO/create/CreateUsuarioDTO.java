package org.example.lifesyncbackend.Domain.DTO.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.lifesyncbackend.Domain.Entity.Rol;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUsuarioDTO {

    @NotBlank @NotNull
    private String nombre;

    @NotNull
    private Integer edad;

    @NotNull
    private Float peso;

    @NotNull
    private Float altura;

    @NotNull
    private Float objetivoPeso;

    @NotBlank @NotNull
    private String genero;

    @NotBlank @NotNull
    private String correo;

    @NotBlank @NotNull
    private String contrasenia;

    private Rol rol;
}
