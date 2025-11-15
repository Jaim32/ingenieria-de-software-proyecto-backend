package org.example.lifesyncbackend.Repository;

import org.example.lifesyncbackend.Domain.DTO.UsuarioDTO;
import org.example.lifesyncbackend.Domain.Entity.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


public interface iUsuarioRepository extends iGenericRepository<Usuario, UUID> {
 Usuario findByIdUsuario(UUID idUsuario);
 Optional<Usuario> findByCorreo(String correo);
 Usuario findByCorreoAndContrasenia(String correo, String contrasenia);
}

