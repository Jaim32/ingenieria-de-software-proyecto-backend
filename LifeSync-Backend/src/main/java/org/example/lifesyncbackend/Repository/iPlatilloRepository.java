package org.example.lifesyncbackend.Repository;

import org.example.lifesyncbackend.Domain.Entity.Hidratacion;
import org.example.lifesyncbackend.Domain.Entity.Platillo;
import org.example.lifesyncbackend.Domain.Entity.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface iPlatilloRepository extends iGenericRepository<Platillo, Long> {
    List<Platillo> findByUsuario_IdUsuario(UUID idUsuario);
    Optional<Platillo> findByUsuarioAndFecha(Usuario usuario, LocalDate fecha);
    List<Platillo> findAllByUsuarioAndFecha(Usuario usuario, LocalDate fecha);
    @Modifying
    @Query("UPDATE Platillo p SET p.Meal = '', p.carbohidrato = '', p.vegetal = '', p.proteina = '', p.fecha = :newDate WHERE p.fecha = :oldDate")
    void resetAllFieldsByFecha(@Param("oldDate") LocalDate oldDate, @Param("newDate") LocalDate newDate);
}

