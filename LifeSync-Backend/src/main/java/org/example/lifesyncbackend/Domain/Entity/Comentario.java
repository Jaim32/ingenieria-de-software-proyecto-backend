package org.example.lifesyncbackend.Domain.Entity;

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
@EntityListeners(AuditingEntityListener.class)
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idComentario;

    @Column(nullable = false, length = 1000)
    private String contenido;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // ===========================
    //   COMENTARIO PARA POST
    // ===========================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_post",
            foreignKey = @ForeignKey(name = "id_post_FK")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;  // <-- Puede ser null si pertenece a una receta

    // ===========================
    //   COMENTARIO PARA RECETA
    // ===========================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_receta",
            foreignKey = @ForeignKey(name = "id_receta_FK")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Receta receta; // <-- Puede ser null si pertenece a un post

    // ===========================
    //   USUARIO AUTOR DEL COMENTARIO
    // ===========================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_usuario",
            nullable = false,
            foreignKey = @ForeignKey(name = "id_usuario_FK")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario user;
}
