package org.example.lifesyncbackend.Scheduling;

import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.response.DailyDataArchiveResponseDTO;
import org.example.lifesyncbackend.Domain.Entity.DailyDataArchive;
import org.example.lifesyncbackend.Domain.Entity.Usuario;
import org.example.lifesyncbackend.Repository.iDailyDataArchiveRepository;
import org.example.lifesyncbackend.Repository.iUsuarioRepository;
import org.example.lifesyncbackend.Service.iDailyDataArchiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DailyArchiveScheduler {
    private static final Logger log = LoggerFactory.getLogger(DailyArchiveScheduler.class);

    private final iUsuarioRepository usuarioRepo;
    private final iDailyDataArchiveService archiveService;

    @Scheduled(fixedRate = 86_400_000, initialDelay = 86_400_000, zone = "America/El_Salvador")
    public void archivarYReiniciarDiario() {
        LocalDate hoy = LocalDate.now();
        usuarioRepo.findAll().forEach(u -> {
            try {
                archiveService.archiveOrUpdate(u.getIdUsuario(), hoy);
                log.info("✔ Archive guardado: usuario={} fecha={}", u.getIdUsuario(), hoy);
            } catch (Exception ex) {
                log.error("✘ Error archivando usuario={} fecha={}: {}", u.getIdUsuario(), hoy, ex.getMessage());
            }
        });
        archiveService.resetAllHydratacionForNextDay(hoy);
        archiveService.resetAllPlatillosForNextDay(hoy);
    }
}