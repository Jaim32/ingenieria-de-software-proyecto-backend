package org.example.lifesyncbackend.Domain.DTO;


import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
public class RecetaDTO {
    private Long idReceta;
    private String nombre;
    private String proteina;
    private String corteProteina;
    private String porcionProteina;
    private String coccionProteina;
    private String carbohidratos;
    private String porcionCarbohidratos;
    private String coccionCarbohidratos;
    private String vegetales;
    private String porcionVegetales;
    private String coccionVegetales;
    private String descripcion;
    private String procedimiento;
    private String imagen;
    private String tipoDeComida;
    private UUID idUsuario;
    private String ingredientesLista;
}

