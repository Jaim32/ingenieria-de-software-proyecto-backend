package org.example.lifesyncbackend.Service.ServiceImplementation;

import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.create.ComentarioCreateDTO;
import org.example.lifesyncbackend.Domain.DTO.response.ComentarioResponseDTO;
import org.example.lifesyncbackend.Domain.DTO.update.ComentarioUpdateDTO;
import org.example.lifesyncbackend.Domain.Entity.Comentario;
import org.example.lifesyncbackend.Domain.Entity.Post;
import org.example.lifesyncbackend.Domain.Entity.Usuario;
import org.example.lifesyncbackend.Repository.iComentarioRepository;
import org.example.lifesyncbackend.Repository.iPostRepository;
import org.example.lifesyncbackend.Repository.iUsuarioRepository;
import org.example.lifesyncbackend.Service.iComentarioService;
// Importa tus excepciones personalizadas (si las tienes) o usa RuntimeException
// import org.example.lifesyncbackend.Domain.Exceptions.ResourceNotFoundException;
// import org.example.lifesyncbackend.Domain.Exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Inyecta las dependencias (final) por constructor
@Transactional // Todos los métodos públicos serán transaccionales
public class ComentarioServiceImpl implements iComentarioService {

    // --- DEPENDENCIAS (REPOSITORIOS) ---
    private final iComentarioRepository iComentarioRepository;
    private final iPostRepository iPostRepository;
    private final iUsuarioRepository iUsuarioRepository;

    // NOTA: Idealmente, aquí inyectarías un Mapper (ej. MapStruct)
    // private final ComentarioMapper comentarioMapper;

    @Override
    public ComentarioResponseDTO createComentario(UUID postId, ComentarioCreateDTO comentarioDTO, UUID idUsuario) {

        // 1. Buscar las entidades relacionadas
        // CAMBIO: Buscamos por ID, no por username
        Usuario usuario = iUsuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + idUsuario)); // Usa tu excepción

        Post post = iPostRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post no encontrado: " + postId)); // Usa tu excepción

        // 2. Crear la nueva entidad Comentario
        Comentario nuevoComentario = Comentario.builder()
                .contenido(comentarioDTO.getContenido())
                .post(post)
                .user(usuario)
                .build();

        // 3. Guardar en la BD
        Comentario comentarioGuardado = iComentarioRepository.save(nuevoComentario);

        // 4. Convertir a DTO de respuesta y devolver
        return convertToDTO(comentarioGuardado);
    }

    @Override
    @Transactional(readOnly = true) // Este método solo lee, no modifica
    public List<ComentarioResponseDTO> getComentariosByPost(UUID postId) {

        if (!iPostRepository.existsById(postId)) {
            throw new RuntimeException("Post no encontrado: " + postId); // Usa tu excepción
        }

        List<Comentario> comentarios = iComentarioRepository.findByPostIdPostOrderByCreatedAtAsc(postId);

        return comentarios.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ComentarioResponseDTO updateComentario(UUID comentarioId, ComentarioUpdateDTO comentarioDTO, UUID idUsuario) {

        Comentario comentario = iComentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado: " + comentarioId));

        // 2. Verificar permisos
        // CAMBIO: Comparamos por ID, no por username
        if (!comentario.getUser().getIdUsuario().equals(idUsuario)) {
            throw new RuntimeException("No tienes permiso para editar este comentario"); // Usa tu excepción
        }

        // 3. Actualizar
        comentario.setContenido(comentarioDTO.getContenido());
        Comentario comentarioActualizado = iComentarioRepository.save(comentario);

        return convertToDTO(comentarioActualizado);
    }

    @Override
    public void deleteComentario(UUID comentarioId, UUID idUsuario) {
        Comentario comentario = iComentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado: " + comentarioId));

        // 2. Verificar permisos
        // CAMBIO: Comparamos por ID, no por username
        if (!comentario.getUser().getIdUsuario().equals(idUsuario)) {
            throw new RuntimeException("No tienes permiso para eliminar este comentario"); // Usa tu excepción
        }

        // 3. Eliminar
        iComentarioRepository.delete(comentario);
    }


    // --- MÉTODO HELPER (Esto debería estar en un Mapper dedicado) ---
    private ComentarioResponseDTO convertToDTO(Comentario comentario) {

        // Aquí deberías mapear al DTO simple del autor
        // UsuarioSimpleDTO autorDTO = new UsuarioSimpleDTO(
        //     comentario.getUser().getIdUsuario(),
        //     comentario.getUser().getUsername() // O el campo que uses para el nombre
        // );

        ComentarioResponseDTO dto = new ComentarioResponseDTO();
        dto.setIdComentario(comentario.getIdComentario());
        dto.setContenido(comentario.getContenido());
        dto.setCreatedAt(comentario.getCreatedAt());
        dto.setPostId(comentario.getPost().getIdPost());
        // dto.setAutor(autorDTO); // <-- Descomentar cuando tengas el mapping del autor

        return dto;
    }
}