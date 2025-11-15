package org.example.lifesyncbackend.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlatilloDTO {
    private Long idPlatillo;
    private String proteina;
    private String carbohidrato;
    private String vegeta;
    private int caloriasTotales;
    private UUID idUsuario;
    private List<Long> ingredientesIds;
}

