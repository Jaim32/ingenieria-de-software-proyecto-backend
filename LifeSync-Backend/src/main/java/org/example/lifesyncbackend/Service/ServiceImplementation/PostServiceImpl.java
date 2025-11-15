package org.example.lifesyncbackend.Service.ServiceImplementation;

import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.create.CreatePostDTO;
import org.example.lifesyncbackend.Domain.DTO.PostDTO;
import org.example.lifesyncbackend.Domain.Entity.Post;
import org.example.lifesyncbackend.Domain.Entity.Usuario;
import org.example.lifesyncbackend.Repository.iPostRepository;
import org.example.lifesyncbackend.Repository.iUsuarioRepository;
import org.example.lifesyncbackend.Service.iPostService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements iPostService {

    private final iPostRepository postRepository;
    private final iUsuarioRepository usuarioRepository;

    @Override
    public PostDTO createPost(CreatePostDTO dto) throws Exception {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());

        // Relacionar con el usuario
        Usuario user = usuarioRepository.findById(dto.getUserId())
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        post.setUser(user);

        // Subida de imagen opcional
        if (dto.getImage() != null) {
            post.setImage("/ruta/a/la/imagen.jpg"); // Simulación de imagen subida
        }

        postRepository.save(post);
        return mapToPostDTO(post);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(this::mapToPostDTO)
                .toList();
    }

    @Override
    public PostDTO getPostById(Long idPost) throws Exception {
        Post post = postRepository.findById(idPost)
                .orElseThrow(() -> new Exception("Post no encontrado"));
        return mapToPostDTO(post);
    }

    @Override
    public PostDTO updatePost(Long idPost, CreatePostDTO dto) throws Exception {
        Post post = postRepository.findById(idPost)
                .orElseThrow(() -> new Exception("Post no encontrado"));

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setUpdatedAt(LocalDateTime.now());

        postRepository.save(post);
        return mapToPostDTO(post);
    }

    @Override
    public PostDTO deletePost(Long idPost) throws Exception {
        Post post = postRepository.findById(idPost)
                .orElseThrow(() -> new Exception("Post no encontrado"));
        postRepository.delete(post);
        return mapToPostDTO(post);
    }

    private PostDTO mapToPostDTO(Post post) {
        PostDTO dto = new PostDTO();
        dto.setIdPost(post.getIdPost());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setImage(post.getImage());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        dto.setUserId(post.getUser().getIdUsuario());
        return dto;
    }
}
