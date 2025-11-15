    package org.example.lifesyncbackend.Domain.DTO.response;

    import com.fasterxml.jackson.annotation.JsonProperty;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.time.LocalDate;
    import java.util.List;
    import java.util.UUID;

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public class PlatilloResponseDTO {
        @JsonProperty("meal")
        private String meal;

        @JsonProperty("proteina")
        private String proteina;

        @JsonProperty("carbohidrato")
        private String carbohidrato;

        @JsonProperty("vegetal")
        private String vegetal;

        @JsonProperty("caloriasTotales")
        private int caloriasTotales;

        @JsonProperty("idUsuario")
        private UUID idUsuario;


        @JsonProperty("fecha")
        private LocalDate fecha;

    }
