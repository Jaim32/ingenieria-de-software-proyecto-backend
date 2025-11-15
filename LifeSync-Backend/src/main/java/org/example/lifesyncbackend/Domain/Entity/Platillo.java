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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Platillo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlatillo;

    @Column
    private String Meal;
    @Column
    private String proteina;

    @Column
    private String carbohidrato;

    @Column
    private String vegetal;

    @Column
    private int caloriasTotales;
    @CreatedDate
    @Column(nullable = false)
    private LocalDate fecha;
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "id_usuario_fk",
            referencedColumnName = "idUsuario",
            columnDefinition = "UUID",
            foreignKey = @ForeignKey(name = "fk_platillo_usuario")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;



}
