package org.example.lifesyncbackend.Domain.Entity; // Asegúrate que el package sea correcto

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class) // Para usar @CreatedDate
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idComentario; // Usamos UUID como en tu entidad Post

    @Column(nullable = false, length = 1000)
    private String contenido;

    @CreatedDate // Usamos la anotación de Spring Auditing
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_post", // Columna de clave foránea en la tabla 'comentarios'
            nullable = false,
            foreignKey = @ForeignKey(name = "id_post_FK")
    )
    @OnDelete(action = OnDeleteAction.CASCADE) // Si se borra el Post, se borra el comentario
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_usuario", // Columna de clave foránea en la tabla 'comentarios'
            nullable = false,
            foreignKey = @ForeignKey(name = "id_usuario_FK")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario user;
}