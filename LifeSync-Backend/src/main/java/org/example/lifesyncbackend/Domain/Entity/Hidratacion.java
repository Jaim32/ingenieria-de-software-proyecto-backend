    package org.example.lifesyncbackend.Domain.Entity;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.hibernate.annotations.OnDelete;
    import org.hibernate.annotations.OnDeleteAction;
    import org.springframework.data.annotation.CreatedDate;
    import org.springframework.data.jpa.domain.support.AuditingEntityListener;

    import java.time.LocalDate;
    import java.util.UUID;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @EntityListeners(AuditingEntityListener.class)
    public class Hidratacion {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idHidratacion;

        @Column
        private boolean estado;
        @CreatedDate
        @Column(nullable = false)
        private LocalDate fecha;

        @Column
        private double progreso;

        /** Esto ya no se persiste, lo calculas al vuelo: */
        @Transient
        private double meta;

        @ManyToOne(optional = false)
        @JoinColumn(
                name = "id_usuario_fk",
                foreignKey = @ForeignKey(name = "fk_hidratacion_usuario")
        )
        @OnDelete(action = OnDeleteAction.CASCADE)
        private Usuario usuario;

    }
