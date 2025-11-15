package org.example.lifesyncbackend.Domain.DTO.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.lifesyncbackend.Domain.Entity.Rol;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private UUID idUsuario;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("edad")
    private Integer edad;

    @JsonProperty("peso")
    private Float peso;

    @JsonProperty("altura")
    private Float altura;

    @JsonProperty("objetivoPeso")
    private Float objetivoPeso;

    @JsonProperty("genero")
    private String genero;

    @JsonProperty("correo")
    private String correo;

    @JsonProperty("contrasenia")
    private String contrasenia;

    @JsonProperty("rol")
    private Rol rol;

}
