package org.example.lifesyncbackend.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Configuración de CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // Deshabilitar CSRF (apropiado para APIs basadas en JWT)
                .csrf(csrf -> csrf.disable())
                // Configuración de sesión sin estado (stateless)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Reglas de autorización
                .authorizeHttpRequests(auth -> auth
                        // Rutas públicas
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/usuarios/create").permitAll()
                        .requestMatchers("/api/recetas/publicas").permitAll()

                        // Rutas posts y comentarios (protegidas)
                        .requestMatchers("/api/posts/**").authenticated()
                        .requestMatchers("/api/posts/*/comentarios").authenticated()
                        .requestMatchers("/api/comentarios/**").authenticated()

                        // Otras rutas protegidas
                        .requestMatchers("/api/platillos/**").authenticated()
                        .requestMatchers("/api/rachas/**").authenticated()
                        .requestMatchers("/api/hidratacion/me/**").authenticated()

                        // Todo lo restante
                        .anyRequest().authenticated()

                )
                // Añadir el filtro JWT antes del filtro de autenticación por defecto
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Configuración de CORS (para permitir solicitudes desde el frontend)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        // Configurar origen de frontend
        config.setAllowedOrigins(List.of("http://localhost:4028")); // Ajusta el origen según tu frontend
        config.setAllowedMethods(List.of("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Aplica CORS a todas las rutas
        return source;
    }
}
