package org.example.lifesyncbackend.Service.ServiceImplementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.lifesyncbackend.Domain.DTO.response.*;
import org.example.lifesyncbackend.Domain.Entity.DailyDataArchive;
import org.example.lifesyncbackend.Domain.Entity.Usuario;
import org.example.lifesyncbackend.Repository.*;
import org.example.lifesyncbackend.Service.iDailyDataArchiveService;
import org.example.lifesyncbackend.Util.HidroCalc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyDataArchiveServiceImpl implements iDailyDataArchiveService {
    private static final Logger log = LoggerFactory.getLogger(DailyDataArchiveServiceImpl.class);

    private final HidroCalc calc;
    private final iUsuarioRepository usuarioRepo;
    private final iDailyDataArchiveRepository archiveRepo;
    private final iHidratacionRepository hidrRepo;
    private final iPlatilloRepository platRepo;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public DailyDataArchiveResponseDTO archiveDaily(UUID usuarioId, LocalDate fecha) throws JsonProcessingException {
        if (archiveRepo.existsByUsuario_IdUsuarioAndFecha(usuarioId, fecha)) {
            throw new IllegalStateException("Ya existe un archivo para usuario=" + usuarioId + " y fecha=" + fecha);
        }
        Usuario user = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + usuarioId));

        DailyDataArchiveResponseDTO dto = buildDto(user, fecha);
        String json = objectMapper.writeValueAsString(dto);
        DailyDataArchive archive = new DailyDataArchive();
        archive.setUsuario(user);
        archive.setFecha(fecha);
        archive.setDatosJson(json);
        archiveRepo.save(archive);
        log.debug("→ archiveDaily guardado JSON: {}", json);
        return dto;
    }

    @Override
    @Transactional
    public DailyDataArchiveResponseDTO archiveOrUpdate(UUID usuarioId, LocalDate fecha) throws JsonProcessingException {
        DailyDataArchive archive = archiveRepo.findByUsuario_IdUsuarioAndFecha(usuarioId, fecha)
                .orElseGet(() -> {
                    Usuario u = usuarioRepo.findById(usuarioId)
                            .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + usuarioId));
                    DailyDataArchive d = new DailyDataArchive();
                    d.setUsuario(u);
                    d.setFecha(fecha);
                    return d;
                });
        DailyDataArchiveResponseDTO dto = buildDto(archive.getUsuario(), fecha);
        String json = objectMapper.writeValueAsString(dto);
        log.debug("→ archiveOrUpdate guardado JSON: {}", json);
        archive.setDatosJson(json);
        archiveRepo.save(archive);
        return dto;
    }

    @Override
    @Transactional
    public DailyDataArchiveResponseDTO getArchive(UUID usuarioId, LocalDate fecha) throws JsonProcessingException {
        DailyDataArchive archive = archiveRepo
                .findByUsuario_IdUsuarioAndFecha(usuarioId, fecha)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe archive para usuario=" + usuarioId + " y fecha=" + fecha));
        return objectMapper.readValue(archive.getDatosJson(), DailyDataArchiveResponseDTO.class);
    }

    @Override
    @Transactional
    public void resetAllHydratacionForNextDay(LocalDate fecha) {
        LocalDate siguiente = fecha.plusDays(1);
        hidrRepo.resetAllByFecha(fecha, siguiente);
        log.info("→ Hidratación reiniciada: fecha antigua={} → nuevaFecha={}", fecha, siguiente);
    }

    @Override
    @Transactional
    public void resetAllPlatillosForNextDay(LocalDate fecha) {
        LocalDate siguiente = fecha.plusDays(1);
        platRepo.resetAllFieldsByFecha(fecha, siguiente);
        log.info("→ Platillos reiniciados: fecha antigua={} → nuevaFecha={}", fecha, siguiente);
    }

    private DailyDataArchiveResponseDTO buildDto(Usuario user, LocalDate fecha) {
        DailyDataArchiveResponseDTO dto = new DailyDataArchiveResponseDTO();
        dto.setIdUsuario(user.getIdUsuario());
        dto.setNombreUsuario(user.getNombre());
        dto.setFecha(fecha);

        hidrRepo.findByUsuarioAndFecha(user, fecha)
                .ifPresent(h -> {
                    double consumidoMl = h.getProgreso();
                    double metaMl      = calc.metaMl(user.getPeso());
                    double rawPct      = (consumidoMl / metaMl) * 100.0;
                    double porcentaje  = Math.min(rawPct, 100.0);
                    boolean completado = rawPct >= 100.0;
                    dto.setHidratacion(new HidratacionResponseDTO(
                            user.getIdUsuario(), h.getFecha(), consumidoMl, metaMl, porcentaje, completado
                    ));
                });
        List<PlatilloResponseDTO> pDtos = platRepo.findAllByUsuarioAndFecha(user, fecha).stream()
                .map(p -> new PlatilloResponseDTO(
                        p.getMeal(),   p.getProteina(), p.getCarbohidrato(), p.getVegetal(), p.getCaloriasTotales(), user.getIdUsuario(), p.getFecha()
                ))
                .collect(Collectors.toList());
        dto.setPlatillos(pDtos);
        return dto;
    }
}