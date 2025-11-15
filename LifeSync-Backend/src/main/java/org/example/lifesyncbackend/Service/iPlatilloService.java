package org.example.lifesyncbackend.Service;

import org.example.lifesyncbackend.Domain.DTO.create.CreatePlatilloDTO;
import org.example.lifesyncbackend.Domain.DTO.response.PlatilloResponseDTO;

import java.util.List;
import java.util.UUID;

public interface iPlatilloService {
    PlatilloResponseDTO createPlatillo(CreatePlatilloDTO dto) throws Exception;
    PlatilloResponseDTO getPlatilloById(Long idPlatillo) throws Exception;
    PlatilloResponseDTO deletePlatillo(Long idPlatillo) throws Exception;
    PlatilloResponseDTO updatePlatillo(Long idPlatillo, CreatePlatilloDTO dto) throws Exception;
    List<PlatilloResponseDTO> getPlatillosByUsuarioId(UUID idUsuario) throws Exception;
}
