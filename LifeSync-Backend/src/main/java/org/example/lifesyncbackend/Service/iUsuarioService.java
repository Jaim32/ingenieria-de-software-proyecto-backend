package org.example.lifesyncbackend.Service;

import jakarta.validation.Valid;
import org.example.lifesyncbackend.Domain.DTO.UsuarioDTO;
import org.example.lifesyncbackend.Domain.DTO.create.CreateUsuarioDTO;
import org.example.lifesyncbackend.Domain.DTO.response.UsuarioResponseDTO;

import java.util.List;
import java.util.UUID;

public interface iUsuarioService {
    UsuarioResponseDTO createUsuario(CreateUsuarioDTO UsuarioDTO) throws Exception;
    UsuarioResponseDTO getUsuarioById(UUID idUsuario) throws Exception;
    UsuarioResponseDTO deleteUsuario(UUID idUsuario) throws Exception;
    UsuarioResponseDTO updateUsuario(UUID idUsuario, CreateUsuarioDTO usuarioDTO) throws Exception;
    List<UsuarioResponseDTO> getAllUsuarios();
    UsuarioResponseDTO updatePesoUsuario(UUID idUsuario, float nuevoPeso) throws Exception;
    //get usuario by correo
    UsuarioResponseDTO getUsuarioByCorreo(String correo) throws Exception;
}
