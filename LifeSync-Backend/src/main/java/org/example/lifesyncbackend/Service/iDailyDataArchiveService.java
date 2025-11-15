// src/main/java/org/example/lifesyncbackend/Service/iDailyDataArchiveService.java
package org.example.lifesyncbackend.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.lifesyncbackend.Domain.DTO.response.DailyDataArchiveResponseDTO;

import java.time.LocalDate;
import java.util.UUID;

public interface iDailyDataArchiveService {


    DailyDataArchiveResponseDTO archiveDaily(UUID usuarioId, LocalDate fecha)
            throws JsonProcessingException, IllegalStateException;


    DailyDataArchiveResponseDTO archiveOrUpdate(UUID usuarioId, LocalDate fecha)
            throws JsonProcessingException;
    DailyDataArchiveResponseDTO getArchive(UUID usuarioId, LocalDate fecha) throws JsonProcessingException;


    void resetAllHydratacionForNextDay(LocalDate fecha);


    void resetAllPlatillosForNextDay(LocalDate fecha);
}

