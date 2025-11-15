package org.example.lifesyncbackend.Domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Racha {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idRacha;

    @CreatedDate
    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private Integer puntos = 0;

    @Column(nullable = false)
    private boolean privilegio = false;

    @Column(name = "last_streak_date")
    private LocalDate lastStreakDate;

    @OneToOne(optional = false)
    @JoinColumn(
            name = "id_usuario_fk",
            referencedColumnName = "idUsuario",
            columnDefinition = "UUID",
            foreignKey = @ForeignKey(name = "fk_racha_usuario")
    )
    @JsonIgnore
    private Usuario usuario;
}
