// HidratacionResponseDTO.java
package org.example.lifesyncbackend.Domain.DTO.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
    public class HidratacionResponseDTO {
        @JsonProperty("idUsuario")
        private UUID idUsuario;

        @JsonProperty("fecha")
        private LocalDate fecha;

        /** Ml totales consumidos hoy */
        @JsonProperty("consumidoMl")
        private double consumidoMl;

        /** Meta diaria en ml (peso×35) */
        @JsonProperty("metaMl")
        private double metaMl;

        /** Porcentaje de cumplimiento 0–100 */
        @JsonProperty("porcentaje")
        private double porcentaje;

        /** True si porcentaje ≥100 */
        @JsonProperty("completado")
        private boolean completado;
    }
