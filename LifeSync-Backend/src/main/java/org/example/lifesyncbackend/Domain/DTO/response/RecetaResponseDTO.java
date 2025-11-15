package org.example.lifesyncbackend.Domain.DTO.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RecetaResponseDTO {
    @JsonProperty("proteina")
    private String proteina;
    @JsonProperty("corteProteina")
    private String corteProteina;
    @JsonProperty("porcionProteina")
    private String porcionProteina;
    @JsonProperty("coccionProteina")
    private String coccionProteina;
    @JsonProperty("carbohidratos")
    private String carbohidratos;
    @JsonProperty("porcionCarbohidratos")
    private String porcionCarbohidratos;
    @JsonProperty("coccionCarbohidratos")
    private String coccionCarbohidratos;
    @JsonProperty("ingredientesLista")
    private String ingredientesLista;
    @JsonProperty("vegetales")
    private String vegetales;
    @JsonProperty("porcionVegetales")
    private String porcionVegetales;
    @JsonProperty("coccionVegetales")
    private String coccionVegetales;
    @JsonProperty("descripcion")
    private String descripcion;
    @JsonProperty("procedimiento")
    private String procedimiento;
    @JsonProperty("imagen")
    private String imagen;
    @JsonProperty("tipoDeComida")
    private String tipoDeComida;
    @JsonProperty("id_Usuario")
    private UUID idUsuario;
    @JsonProperty("fecha")
    private LocalDate fecha;
    @JsonProperty("aprobada")
    private boolean aprobada;
    @JsonProperty("idReceta")
    private Long idReceta;
    @JsonProperty("nombre")
    private String nombre;
}
