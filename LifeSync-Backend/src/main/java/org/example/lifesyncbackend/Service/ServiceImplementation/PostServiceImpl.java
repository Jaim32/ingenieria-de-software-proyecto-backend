package org.example.lifesyncbackend.Service.ServiceImplementation;

import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.create.CreatePostDTO;
import org.example.lifesyncbackend.Domain.DTO.response.PostResponseDTO;
import org.example.lifesyncbackend.Domain.Entity.Post;
import org.example.lifesyncbackend.Domain.Entity.Usuario;
import org.example.lifesyncbackend.Repository.iPostRepository;
import org.example.lifesyncbackend.Repository.iUsuarioRepository;
import org.example.lifesyncbackend.Service.iPostService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements iPostService {

    private final iPostRepository postRepository;
    private final iUsuarioRepository usuarioRepository;

    @Override
    public PostResponseDTO createPost(CreatePostDTO dto) throws Exception {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        post.setType(dto.getType());

        // Relacionar con el usuario
        Usuario user = usuarioRepository.findById(dto.getUserId())
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        post.setUser(user);

        post.setImage(dto.getImage());

        postRepository.save(post);
        return mapToPostResponseDTO(post);
    }

    @Override
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(this::mapToPostResponseDTO)
                .toList();
    }

    @Override
    public PostResponseDTO getPostById(UUID idPost) throws Exception {
        Post post = postRepository.findById(idPost)
                .orElseThrow(() -> new Exception("Post no encontrado"));
        return mapToPostResponseDTO(post);
    }

    @Override
    public PostResponseDTO updatePost(UUID idPost, CreatePostDTO dto) throws Exception {
        Post post = postRepository.findById(idPost)
                .orElseThrow(() -> new Exception("Post no encontrado"));

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setUpdatedAt(LocalDateTime.now());
        post.setType(dto.getType());

        postRepository.save(post);
        return mapToPostResponseDTO(post);
    }

    @Override
    public PostResponseDTO deletePost(UUID idPost) throws Exception {
        Post post = postRepository.findById(idPost)
                .orElseThrow(() -> new Exception("Post no encontrado"));
        postRepository.delete(post);
        return mapToPostResponseDTO(post);
    }

    @Override
    public List<PostResponseDTO> getPostsByUserId(UUID userId) {
        List<Post> posts = postRepository.findAllByUser_IdUsuario(userId);  // Usamos un método en el repositorio para encontrar los posts por usuario

        return posts.stream()
                .map(this::mapToPostResponseDTO)
                .toList();
    }

    @Override
    public List<PostResponseDTO> getPostsByType(String type) {
        List<Post> posts = postRepository.findAllByType(type);
        return posts.stream()
                .map(this::mapToPostResponseDTO)
                .toList();
    }



    private PostResponseDTO mapToPostResponseDTO(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setIdPost(post.getIdPost());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setImage(post.getImage());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        dto.setUserId(post.getUser().getIdUsuario());
        dto.setType(post.getType());
        dto.setNombreUsuario(post.getUser().getNombre());

        return dto;
    }
}
