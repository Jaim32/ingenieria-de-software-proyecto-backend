package org.example.lifesyncbackend.Domain.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class HidratacionDTO {
    private Long idHidratacion;
    private boolean estado;
    private double progreso;
    private UUID idUsuario;
    private double metaDiaria;  // en ml
}
