package org.example.lifesyncbackend.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RachaDTO {
    private long idRacha;
    private Integer puntos;
    private boolean privilegio;
}

