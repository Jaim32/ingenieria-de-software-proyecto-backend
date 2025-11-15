package org.example.lifesyncbackend.Domain.DTO.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
public class CreatePostDTO {

    @NotBlank
    @NotEmpty
    @NotNull
    private String title;

    @NotBlank
    @NotEmpty
    @NotNull
    private String content;

    private MultipartFile image; // Imagen


    @NotBlank
    @NotEmpty
    @NotNull
    private String type; // Tipo de publicación

    @NotNull
    private UUID userId;
}
