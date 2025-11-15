package org.example.lifesyncbackend.Service;

import org.example.lifesyncbackend.Domain.DTO.create.CreatePostDTO;
import org.example.lifesyncbackend.Domain.DTO.response.PostResponseDTO;

import java.util.List;

public interface iPostService {
    PostResponseDTO createPost(CreatePostDTO postDTO) throws Exception;
    List<PostResponseDTO> getAllPosts();
    PostResponseDTO getPostById(Long idPost) throws Exception;
    PostResponseDTO updatePost(Long idPost, CreatePostDTO postDTO) throws Exception;
    PostResponseDTO deletePost(Long idPost) throws Exception;
}
