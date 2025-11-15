package org.example.lifesyncbackend.Domain.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.lifesyncbackend.Domain.Entity.Rol;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idUsuario;

    @Column(nullable = false)
    private String nombre;

    @Column
    private Integer edad;

    @Column
    private Float peso;

    @Column
    private Float altura;

    @Column
    private Float objetivoPeso;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @org.hibernate.annotations.ColumnDefault("'USER'")
    private Rol rol = Rol.USER;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_racha_fk")
    private Racha racha;

    public void promoverAPremium() {
        this.rol = Rol.PREMIUM;
    }
}
