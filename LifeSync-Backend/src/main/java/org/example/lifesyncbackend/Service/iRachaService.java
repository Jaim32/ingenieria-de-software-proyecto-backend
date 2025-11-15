package org.example.lifesyncbackend.Service;

import org.example.lifesyncbackend.Domain.DTO.create.CreateRachaDTO;
import org.example.lifesyncbackend.Domain.DTO.response.RachaResponseDTO;

import java.util.UUID;

public interface iRachaService {
    RachaResponseDTO createRacha(CreateRachaDTO dto) throws Exception;
    RachaResponseDTO getRachaById(Long id) throws Exception;
    RachaResponseDTO getRachaByUsuarioId(UUID idUsuario) throws Exception;
    RachaResponseDTO updateRacha(Long id, CreateRachaDTO dto) throws Exception;
    RachaResponseDTO deleteRacha(Long id) throws Exception;
    RachaResponseDTO updateByUsuarioId(UUID idUsuario, CreateRachaDTO dto) throws Exception;
    RachaResponseDTO evaluarYPromover(UUID idUsuario) throws Exception;
    void registrarInicioSesion(UUID idUsuario) throws Exception;
}