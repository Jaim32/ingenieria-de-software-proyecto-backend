package org.example.lifesyncbackend.Domain.DTO;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PostDTO {

    private Long idPost;
    private String title;
    private String content;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;
}
