package org.example.lifesyncbackend.Service.ServiceImplementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.create.CreateUsuarioDTO;
import org.example.lifesyncbackend.Domain.DTO.response.UsuarioResponseDTO;
import org.example.lifesyncbackend.Domain.Entity.Hidratacion;
import org.example.lifesyncbackend.Domain.Entity.Racha;
import org.example.lifesyncbackend.Domain.Entity.Rol;
import org.example.lifesyncbackend.Domain.Entity.Usuario;
import org.example.lifesyncbackend.Repository.iHidratacionRepository;
import org.example.lifesyncbackend.Repository.iRachaRepository;
import org.example.lifesyncbackend.Repository.iUsuarioRepository;
import org.example.lifesyncbackend.Service.iUsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements iUsuarioService {

    private final iUsuarioRepository usuarioRepository;
    private final iRachaRepository rachaRepository;
    private final iHidratacionRepository hidratacionRepository;

    @Override
    @Transactional
    public UsuarioResponseDTO createUsuario(CreateUsuarioDTO dto) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasenia(dto.getContrasenia());
        usuario.setNombre(dto.getNombre());
        usuario.setEdad(dto.getEdad());
        usuario.setPeso(dto.getPeso());
        usuario.setAltura(dto.getAltura());
        usuario.setObjetivoPeso(dto.getObjetivoPeso());
        usuario.setGenero(dto.getGenero());
        usuario.setRol(dto.getRol() != null ? dto.getRol() : Rol.USER);

        usuario = usuarioRepository.save(usuario);                        // UUID listo

        /* 2. Crear racha inicial */
        Racha racha = new Racha();
        racha.setPuntos(0);
        racha.setPrivilegio(false);
        racha.setUsuario(usuario);
        racha = rachaRepository.save(racha);

        /* 3. Vincular racha al usuario */
        usuario.setRacha(racha);
        usuario = usuarioRepository.save(usuario);

        /* 4. Crear hidratación inicial */
        Hidratacion hidratacion = new Hidratacion();
        hidratacion.setEstado(false);
        hidratacion.setProgreso(0);
        hidratacion.setUsuario(usuario);
        hidratacionRepository.save(hidratacion);

        return mapToUsuarioResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO getUsuarioById(UUID id) throws Exception {
        return mapToUsuarioResponseDTO(usuarioRepository.findByIdOrThrows(id));
    }

    @Override
    public UsuarioResponseDTO getUsuarioByCorreo(String correo) throws Exception {
        return mapToUsuarioResponseDTO(
                usuarioRepository.findByCorreo(correo)
                        .orElseThrow(() -> new Exception("Usuario no encontrado con correo: " + correo)));
    }

    @Override
    public List<UsuarioResponseDTO> getAllUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::mapToUsuarioResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateUsuario(UUID id, CreateUsuarioDTO dto) throws Exception {
        Usuario usuario = usuarioRepository.findByIdOrThrows(id);

        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasenia(dto.getContrasenia());
        usuario.setNombre(dto.getNombre());
        usuario.setEdad(dto.getEdad());
        usuario.setPeso(dto.getPeso());
        usuario.setAltura(dto.getAltura());
        usuario.setObjetivoPeso(dto.getObjetivoPeso());
        usuario.setGenero(dto.getGenero());
        if (dto.getRol() != null) usuario.setRol(dto.getRol());

        return mapToUsuarioResponseDTO(usuarioRepository.save(usuario));
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updatePesoUsuario(UUID id, float nuevoPeso) throws Exception {
        Usuario usuario = usuarioRepository.findByIdOrThrows(id);
        usuario.setPeso(nuevoPeso);
        return mapToUsuarioResponseDTO(usuarioRepository.save(usuario));
    }

    @Override
    @Transactional
    public UsuarioResponseDTO deleteUsuario(UUID id) throws Exception {
        Usuario usuario = usuarioRepository.findByIdOrThrows(id);
        usuarioRepository.delete(usuario);
        return mapToUsuarioResponseDTO(usuario);
    }

    private UsuarioResponseDTO mapToUsuarioResponseDTO(Usuario u) {
        return new UsuarioResponseDTO(
                u.getIdUsuario(),
                u.getNombre(),
                u.getEdad(),
                u.getPeso(),
                u.getAltura(),
                u.getObjetivoPeso(),
                u.getGenero(),
                u.getCorreo(),
                u.getContrasenia(),
                u.getRol()
        );
    }
}
