package org.example.lifesyncbackend.Config;

import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Service.iRachaService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthenticationSuccessListener
        implements ApplicationListener<AuthenticationSuccessEvent> {

    private final iRachaService rachaService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {

        Object principal = event.getAuthentication().getPrincipal();

        if (principal instanceof UserDetails details) {
            try {
                UUID uid = UUID.fromString(details.getUsername());   
                rachaService.registrarInicioSesion(uid);            
            } catch (IllegalArgumentException ignored) {
                
            } catch (Exception e) {
                e.printStackTrace();         
            }
        }
    }
}
