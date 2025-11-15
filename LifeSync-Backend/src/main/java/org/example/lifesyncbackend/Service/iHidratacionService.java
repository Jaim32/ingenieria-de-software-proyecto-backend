    package org.example.lifesyncbackend.Service;


    import org.example.lifesyncbackend.Domain.DTO.response.HidratacionResponseDTO;
    import java.util.UUID;

    public interface iHidratacionService {
        /**
         * Obtiene el estado de hidratación del usuario para hoy.
         */
        HidratacionResponseDTO getHidratacionByUserId(UUID idUsuario) throws Exception;

        /**
         * Agrega mililitros al progreso diario y devuelve el estado actualizado.
         */
        HidratacionResponseDTO updateProgresoHidratacion(UUID idUsuario, double ml) throws Exception;
    }
