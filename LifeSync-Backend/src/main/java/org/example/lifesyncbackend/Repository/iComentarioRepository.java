package org.example.lifesyncbackend.Repository; // Asegúrate que el package sea correcto

import org.example.lifesyncbackend.Domain.Entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface iComentarioRepository extends JpaRepository<Comentario, UUID> {

    /**
     * Busca todos los comentarios de un Post específico, usando el ID del post.
     * Spring Data JPA entiende este nombre de método:
     * "findBy" + "Post" (el campo en Comentario) + "IdPost" (el campo en Post)
     * "OrderBy" + "CreatedAt" + "Asc" (para ordenarlos del más antiguo al más nuevo)
     */
    List<Comentario> findByPostIdPostOrderByCreatedAtAsc(UUID postId);

}