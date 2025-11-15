package org.example.lifesyncbackend.Repository;



import org.example.lifesyncbackend.Domain.Entity.DailyDataArchive;
import org.example.lifesyncbackend.Domain.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface iDailyDataArchiveRepository extends JpaRepository<DailyDataArchive, Long> {

    boolean existsByUsuario_IdUsuarioAndFecha(UUID usuarioId, LocalDate fecha);
    Optional<DailyDataArchive> findByUsuario_IdUsuarioAndFecha(UUID usuarioId, LocalDate fecha);

}
