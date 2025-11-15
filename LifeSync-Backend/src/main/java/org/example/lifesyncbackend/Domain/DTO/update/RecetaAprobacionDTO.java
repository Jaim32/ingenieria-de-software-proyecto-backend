package org.example.lifesyncbackend.Domain.DTO.update;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecetaAprobacionDTO {
    @NotNull
    private Boolean aprobada;
}
