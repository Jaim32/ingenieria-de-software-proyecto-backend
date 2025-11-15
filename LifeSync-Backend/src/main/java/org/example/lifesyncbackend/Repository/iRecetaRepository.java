package org.example.lifesyncbackend.Repository;

import org.example.lifesyncbackend.Domain.Entity.Receta;
import org.example.lifesyncbackend.Domain.Entity.Usuario;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface iRecetaRepository extends iGenericRepository<Receta, Long> {


    Optional<Receta> findByUsuarioAndFecha(Usuario usuario, LocalDate fecha);
    List<Receta>     findAllByUsuarioAndFecha(Usuario usuario, LocalDate fecha);
    List<Receta> findByAprobadaTrue();
    List<Receta> findByAprobadaFalse();
    List<Receta> findAllByUsuario(Usuario usuario);




}