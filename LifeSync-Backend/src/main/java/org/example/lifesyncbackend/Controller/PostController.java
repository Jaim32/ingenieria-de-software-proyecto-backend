package org.example.lifesyncbackend.Controller;

import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.create.CreatePostDTO;
import org.example.lifesyncbackend.Domain.DTO.response.PostResponseDTO;
import org.example.lifesyncbackend.Service.iPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final iPostService postService;

    // Endpoint para crear una publicación
    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody CreatePostDTO postDTO) throws Exception {
        // Al crear un post, ahora también se maneja el campo "type" que es el tipo de post
        PostResponseDTO createdPost = postService.createPost(postDTO);
        return ResponseEntity.status(201).body(createdPost); // Se retorna con código 201 (creado)
    }

    // Endpoint para obtener todas las publicaciones
    @GetMapping
    public List<PostResponseDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    // Endpoint para obtener una publicación por su ID
    @GetMapping("/{idPost}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable UUID idPost) throws Exception {
        PostResponseDTO post = postService.getPostById(idPost);
        return ResponseEntity.ok(post); // Se retorna con código 200 (OK)
    }

    // Endpoint para actualizar una publicación por su ID
    @PutMapping("/{idPost}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable UUID idPost, @RequestBody CreatePostDTO postDTO) throws Exception {
        // Al actualizar un post, también se actualiza el campo "type" del post
        PostResponseDTO updatedPost = postService.updatePost(idPost, postDTO);
        return ResponseEntity.ok(updatedPost); // Se retorna con código 200 (OK)
    }

    // Endpoint para eliminar una publicación por su ID
    @DeleteMapping("/{idPost}")
    public ResponseEntity<PostResponseDTO> deletePost(@PathVariable UUID idPost) throws Exception {
        PostResponseDTO deletedPost = postService.deletePost(idPost);
        return ResponseEntity.ok(deletedPost); // Se retorna con código 200 (OK)
    }

    // Endpoint para obtener publicaciones de un usuario específico
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponseDTO>> getPostsByUser(@PathVariable UUID userId) {
        List<PostResponseDTO> posts = postService.getPostsByUserId(userId);
        return ResponseEntity.ok(posts);  // Devuelve las publicaciones del usuario
    }
}
