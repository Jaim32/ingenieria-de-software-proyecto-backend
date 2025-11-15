package org.example.lifesyncbackend.Service;

import org.example.lifesyncbackend.Domain.DTO.create.CreatePostDTO;
import org.example.lifesyncbackend.Domain.DTO.response.PostResponseDTO;

import java.util.List;
import java.util.UUID;

public interface iPostService {
    PostResponseDTO createPost(CreatePostDTO postDTO) throws Exception;
    List<PostResponseDTO> getAllPosts();
    PostResponseDTO getPostById(UUID idPost) throws Exception;
    PostResponseDTO updatePost(UUID idPost, CreatePostDTO postDTO) throws Exception;
    PostResponseDTO deletePost(UUID idPost) throws Exception;
    List<PostResponseDTO> getPostsByUserId(UUID userId);
}
