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

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @EntityListeners(AuditingEntityListener.class)
    public class Receta {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long idReceta;
        @Column
        private String nombre;
        @Column
        private String proteina;
        @CreatedDate
        @Column(nullable = false)
        private LocalDate fecha;
        @Column
        private String corteProteina;
        @Column
        private String porcionProteina;
        @Column
        private String coccionProteina;
        @Column
        private String carbohidratos;
        @Column
        private String porcionCarbohidratos;
        @Column
        private String coccionCarbohidratos;
        @Column
        private String ingredientesLista;
        @Column
        private String vegetales;
        @Column
        private String porcionVegetales;
        @Column
        private String coccionVegetales;
        @Column
        private String descripcion;
        @Column
        private String procedimiento;
        @Column
        private String imagen;
        @Column
        private String tipoDeComida;
        @Column(nullable = false)
        private boolean aprobada = false;

        @ManyToOne
        @JoinColumn(
                name = "id_usuario",
                foreignKey = @ForeignKey(name = "id_usuario_FK")
        )
        @OnDelete(action = OnDeleteAction.CASCADE)
        private Usuario usuario;


    }
