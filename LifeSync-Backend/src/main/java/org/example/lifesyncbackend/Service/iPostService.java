package org.example.lifesyncbackend.Service;

import org.example.lifesyncbackend.Domain.DTO.create.CreatePostDTO;
import org.example.lifesyncbackend.Domain.DTO.PostDTO;
import org.example.lifesyncbackend.Domain.Entity.Post;

import java.util.List;

public interface iPostService {
    PostDTO createPost(CreatePostDTO postDTO) throws Exception;
    List<PostDTO> getAllPosts();
    PostDTO getPostById(Long idPost) throws Exception;
    PostDTO updatePost(Long idPost, CreatePostDTO postDTO) throws Exception;
    PostDTO deletePost(Long idPost) throws Exception;
}
