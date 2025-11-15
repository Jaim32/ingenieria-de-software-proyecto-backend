package org.example.lifesyncbackend.Repository;

import org.example.lifesyncbackend.Domain.Entity.Hidratacion;
import org.example.lifesyncbackend.Domain.Entity.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface iHidratacionRepository extends iGenericRepository<Hidratacion, Long> {
    Optional<Hidratacion> findByUsuario_IdUsuario(UUID idUsuario);

    //actualizar el estado de hidratación por usuario
    Optional<Hidratacion> findByUsuario(Usuario usuario);

    Optional<Hidratacion> findByUsuarioAndFecha(Usuario usuario, LocalDate fecha);

    List<Hidratacion> findAllByUsuarioAndFecha(Usuario usuario, LocalDate fecha);

    @Modifying
    @Query("UPDATE Hidratacion h SET h.fecha = :newDate, h.progreso = 0, h.estado = false WHERE h.fecha = :oldDate")
    void resetAllByFecha(@Param("oldDate") LocalDate oldDate, @Param("newDate") LocalDate newDate);
}
