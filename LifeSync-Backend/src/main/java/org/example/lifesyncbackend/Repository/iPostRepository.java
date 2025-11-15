package org.example.lifesyncbackend.Repository;

import org.example.lifesyncbackend.Domain.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iPostRepository extends JpaRepository<Post, Long> {
}
