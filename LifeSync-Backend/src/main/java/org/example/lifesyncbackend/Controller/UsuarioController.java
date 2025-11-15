package org.example.lifesyncbackend.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.GenericResponse;
import org.example.lifesyncbackend.Domain.DTO.create.CreateUsuarioDTO;
import org.example.lifesyncbackend.Domain.DTO.response.UsuarioResponseDTO;
import org.example.lifesyncbackend.Service.iUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.example.lifesyncbackend.Util.Constants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API + USUARIO_CONTROLLER)
public class UsuarioController {

    private final iUsuarioService usuarioService;

    @PostMapping(CREATE)
    public ResponseEntity<GenericResponse> createUsuario(
            @RequestBody @Valid CreateUsuarioDTO object) throws Exception {

        UsuarioResponseDTO data = usuarioService.createUsuario(object);

        return GenericResponse.builder()
                .message("Usuario creado exitosamente")
                .data(data)
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<GenericResponse> deleteUsuario(
            @RequestParam UUID idUsuario) throws Exception {

        UsuarioResponseDTO data = usuarioService.deleteUsuario(idUsuario);

        return GenericResponse.builder()
                .message("Usuario eliminado exitosamente")
                .data(data)
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }

    @GetMapping(GET_BY_ID)
    public ResponseEntity<GenericResponse> getUsuarioById(
            @RequestParam UUID idUsuario) throws Exception {

        UsuarioResponseDTO data = usuarioService.getUsuarioById(idUsuario);

        return GenericResponse.builder()
                .message("Usuario encontrado exitosamente")
                .data(data)
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }

    @PutMapping("/update")
    public ResponseEntity<UsuarioResponseDTO> updateUsuario(
            @RequestParam UUID idUsuario,
            @RequestBody @Valid CreateUsuarioDTO usuarioDTO) throws Exception {

        UsuarioResponseDTO updated = usuarioService.updateUsuario(idUsuario, usuarioDTO);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsuarioResponseDTO>> getAllUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PatchMapping("/{idUsuario}/peso")
    public ResponseEntity<UsuarioResponseDTO> updatePesoUsuario(
            @PathVariable UUID idUsuario,
            @RequestParam float nuevoPeso) throws Exception {

        return ResponseEntity.ok(usuarioService.updatePesoUsuario(idUsuario, nuevoPeso));
    }
}
