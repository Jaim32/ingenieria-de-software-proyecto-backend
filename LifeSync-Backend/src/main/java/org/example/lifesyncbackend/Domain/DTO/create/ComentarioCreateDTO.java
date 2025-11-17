package org.example.lifesyncbackend.Domain.DTO.create; // Asegúrate que el package sea correcto

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioCreateDTO {

    @NotBlank(message = "El contenido no puede estar vacío.")
    @Size(max = 1000, message = "El comentario no puede exceder los 1000 caracteres.")
    private String contenido;

}