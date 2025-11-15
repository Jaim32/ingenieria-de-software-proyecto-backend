package org.example.lifesyncbackend.Repository;

import org.example.lifesyncbackend.Domain.Entity.Racha;
import org.example.lifesyncbackend.Domain.Entity.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface iRachaRepository extends iGenericRepository<Racha, Long> {
    Optional<Racha> findByUsuario_IdUsuario(java.util.UUID idUsuario);
    Optional<Racha> findByUsuarioAndFecha(Usuario usuario, LocalDate fecha);

}
