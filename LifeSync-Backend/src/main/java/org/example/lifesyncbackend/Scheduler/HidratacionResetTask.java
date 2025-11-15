package org.example.lifesyncbackend.Scheduler;

import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.Entity.Hidratacion;
import org.example.lifesyncbackend.Repository.iHidratacionRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;   // ⇦ importa el de Spring

import java.util.List;

@Component
@RequiredArgsConstructor
public class HidratacionResetTask {

    private final iHidratacionRepository hidrRepo;

    /** 00:00 cada día */
    @Scheduled(cron = "0 0 0 * * *", zone = "America/El_Salvador")
    @Transactional            // ⇦ Spring abre y cierra la TX
    public void resetearProgresoDiario() {

        List<Hidratacion> todas = hidrRepo.findAll();   // entidades *managed*
        todas.forEach(h -> {
            h.setProgreso(0.0);      // mejor double
            h.setEstado(false);
        });
        // Al terminar el método:
        // 1) Spring hace flush
        // 2) Commit -> UPDATE en la BD
        System.out.println("[RESET] Progreso de hidratación reiniciado");
    }
}
