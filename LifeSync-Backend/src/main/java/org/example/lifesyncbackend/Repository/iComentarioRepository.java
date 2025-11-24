package org.example.lifesyncbackend.Repository;

import org.example.lifesyncbackend.Domain.Entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface iComentarioRepository extends JpaRepository<Comentario, UUID> {

    // Comentarios de Post
    List<Comentario> findByPostIdPostOrderByCreatedAtAsc(UUID postId);

    // Comentarios de Receta
    List<Comentario> findByRecetaIdRecetaOrderByCreatedAtAsc(Long recetaId);

}
