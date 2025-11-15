package org.example.lifesyncbackend.Domain.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPost;

    @Column(nullable = false, length = 80)
    private String title; // Título de la publicación

    @Column(nullable = false, length = 2000)
    private String content; // Contenido de la publicación

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt; // Fecha de creación

    @Column
    private LocalDateTime updatedAt; // Fecha de última actualización

    @Column
    private String image; // Imagen opcional (ruta de la imagen)

    @ManyToOne
    @JoinColumn(
            name = "id_usuario",
            foreignKey = @ForeignKey(name = "id_usuario_FK")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario user; // Relación con la entidad Usuario

    // En caso de ser necesario, otros campos como 'tipoDePost' pueden ser añadidos
}
