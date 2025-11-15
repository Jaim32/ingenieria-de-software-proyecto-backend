package org.example.lifesyncbackend.Controller;

import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.create.CreateLoginDTO;
import org.example.lifesyncbackend.Domain.DTO.response.LoginResponseDTO;
import org.example.lifesyncbackend.Domain.Entity.Usuario;
import org.example.lifesyncbackend.Repository.iUsuarioRepository;
import org.example.lifesyncbackend.Service.iRachaService;
import org.example.lifesyncbackend.Util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final iUsuarioRepository usuarioRepository;
    private final iRachaService      rachaService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CreateLoginDTO loginDTO) {

        Usuario usuario = usuarioRepository.findByCorreoAndContrasenia(
                loginDTO.getCorreo(),
                loginDTO.getContrasenia()
        );

        if (usuario == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales inválidas");
        }

        try {
            rachaService.registrarInicioSesion(usuario.getIdUsuario());
        } catch (Exception e) {
            e.printStackTrace();   // o reemplaza por tu logger
        }

        String token = jwtUtil.generateToken(
                usuario.getIdUsuario(),
                usuario.getRol().name()
        );


        LoginResponseDTO response = new LoginResponseDTO(
                token,
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getCorreo()
        );

        return ResponseEntity.ok(response);
    }
}
