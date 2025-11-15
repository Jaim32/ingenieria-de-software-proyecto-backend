package org.example.lifesyncbackend.Repository;

import org.example.lifesyncbackend.Domain.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface iPostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByUser_IdUsuario(UUID userId);  // Cambiar de 'User_Id' a 'User_IdUsuario'
}
