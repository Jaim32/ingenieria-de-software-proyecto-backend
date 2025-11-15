package org.example.lifesyncbackend.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.lifesyncbackend.Domain.Entity.Rol;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private UUID idUsuario;
    private String contrasenia;
    private String nombre;
    private Integer edad;
    private Float peso;
    private Float altura;
    private Float objetivoPeso;
    private String genero;
    private String correo;
    private Rol rol;
}
