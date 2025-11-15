package org.example.lifesyncbackend.Domain.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;

    private UUID idUsuario;
    private String nombre;
    private String correo;
}
