package org.example.lifesyncbackend.Service.ServiceImplementation;

import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.create.ComentarioCreateDTO;
import org.example.lifesyncbackend.Domain.DTO.response.ComentarioResponseDTO;
import org.example.lifesyncbackend.Domain.DTO.update.ComentarioUpdateDTO;
import org.example.lifesyncbackend.Domain.Entity.Comentario;
import org.example.lifesyncbackend.Domain.Entity.Post;
import org.example.lifesyncbackend.Domain.Entity.Receta;
import org.example.lifesyncbackend.Domain.Entity.Usuario;
import org.example.lifesyncbackend.Repository.iComentarioRepository;
import org.example.lifesyncbackend.Repository.iPostRepository;
import org.example.lifesyncbackend.Repository.iRecetaRepository;
import org.example.lifesyncbackend.Repository.iUsuarioRepository;
import org.example.lifesyncbackend.Service.iComentarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ComentarioServiceImpl implements iComentarioService {

    private final iComentarioRepository iComentarioRepository;
    private final iPostRepository iPostRepository;
    private final iUsuarioRepository iUsuarioRepository;
    private final iRecetaRepository iRecetaRepository;

    // ==========================================================
    //                  COMENTARIOS EN POSTS
    // ==========================================================
    @Override
    public ComentarioResponseDTO createComentario(UUID postId, ComentarioCreateDTO comentarioDTO, UUID idUsuario) {

        Usuario usuario = iUsuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + idUsuario));

        Post post = iPostRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post no encontrado: " + postId));

        Comentario comentario = Comentario.builder()
                .contenido(comentarioDTO.getContenido())
                .post(post)
                .user(usuario)
                .build();

        return convertToDTO(iComentarioRepository.save(comentario));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComentarioResponseDTO> getComentariosByPost(UUID postId) {

        if (!iPostRepository.existsById(postId)) {
            throw new RuntimeException("Post no encontrado: " + postId);
        }

        return iComentarioRepository.findByPostIdPostOrderByCreatedAtAsc(postId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ==========================================================
    //                  COMENTARIOS EN RECETAS
    // ==========================================================
    @Override
    public ComentarioResponseDTO createComentarioEnReceta(Long recetaId, ComentarioCreateDTO dto, UUID idUsuario) {

        Usuario usuario = iUsuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + idUsuario));

        Receta receta = iRecetaRepository.findById(recetaId)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada: " + recetaId));

        Comentario comentario = Comentario.builder()
                .contenido(dto.getContenido())
                .receta(receta)
                .user(usuario)
                .build();

        return convertToDTO(iComentarioRepository.save(comentario));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComentarioResponseDTO> getComentariosByReceta(Long recetaId) {

        if (!iRecetaRepository.existsById(recetaId)) {
            throw new RuntimeException("Receta no encontrada: " + recetaId);
        }

        return iComentarioRepository.findByRecetaIdRecetaOrderByCreatedAtAsc(recetaId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ==========================================================
    //                     UPDATE & DELETE
    // ==========================================================
    @Override
    public ComentarioResponseDTO updateComentario(UUID comentarioId, ComentarioUpdateDTO comentarioDTO, UUID idUsuario) {

        Comentario comentario = iComentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado: " + comentarioId));

        if (!comentario.getUser().getIdUsuario().equals(idUsuario)) {
            throw new RuntimeException("No tienes permiso para editar este comentario");
        }

        comentario.setContenido(comentarioDTO.getContenido());

        return convertToDTO(iComentarioRepository.save(comentario));
    }

    @Override
    public void deleteComentario(UUID comentarioId, UUID idUsuario) {

        Comentario comentario = iComentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado: " + comentarioId));

        if (!comentario.getUser().getIdUsuario().equals(idUsuario)) {
            throw new RuntimeException("No tienes permiso para eliminar este comentario");
        }

        iComentarioRepository.delete(comentario);
    }

    // ==========================================================
    //                        DTO MAPPER
    // ==========================================================
    private ComentarioResponseDTO convertToDTO(Comentario comentario) {

        ComentarioResponseDTO dto = new ComentarioResponseDTO();

        dto.setIdComentario(comentario.getIdComentario());
        dto.setContenido(comentario.getContenido());
        dto.setCreatedAt(comentario.getCreatedAt());

        // Identificador dinámico según el tipo de comentario
        dto.setPostId(comentario.getPost() != null ? comentario.getPost().getIdPost() : null);
        dto.setRecetaId(comentario.getReceta() != null ? comentario.getReceta().getIdReceta() : null);

        // Usuario autor
        dto.setIdUser(comentario.getUser().getIdUsuario());
        dto.setNombreUsuario(comentario.getUser().getNombre());

        return dto;
    }
}
