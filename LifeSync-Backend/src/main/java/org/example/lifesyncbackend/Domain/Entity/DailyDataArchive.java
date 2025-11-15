package org.example.lifesyncbackend.Domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
        name = "daily_data_archive",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_daily_user_fecha",
                columnNames = { "id_usuario_fk", "fecha" }
        ),
        indexes = {
                @Index(name = "idx_archive_usuario_fecha", columnList = "id_usuario_fk, fecha")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class DailyDataArchive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArchive;
    @CreatedDate
    @Column(nullable = false)
    private LocalDate fecha;

    @ManyToOne(optional = true)
    @JoinColumn(
            name = "id_usuario_fk",
            referencedColumnName = "idUsuario",
            columnDefinition = "UUID",
            foreignKey = @ForeignKey(name = "fk_archive_usuario")
    )
    @JsonIgnore
    private Usuario usuario;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String datosJson;


}

