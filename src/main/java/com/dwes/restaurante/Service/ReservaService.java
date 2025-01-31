package com.dwes.restaurante.Service;

import com.dwes.restaurante.DTO.ReservaDTO;
import com.dwes.restaurante.Entity.Reserva;
import com.dwes.restaurante.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    /** Verifica si una mesa está disponible en una fecha y hora específicas */
    public boolean verificarDisponibilidad(Long mesaId, LocalDateTime fecha) {
        return !reservaRepository.existsByMesaIdAndFecha(mesaId, fecha);
    }

    public List<ReservaDTO> getReservasPorDia(LocalDate fecha) {
        // Convertimos LocalDate a LocalDateTime al inicio del día
        LocalDateTime inicioDia = fecha.atStartOfDay();

        // Obtenemos las reservas de la base de datos
        List<Reserva> reservas = reservaRepository.findByFecha(inicioDia);

        // Convertimos las reservas a DTOs
        return reservas.stream()
                .map(r -> new ReservaDTO(
                        r.getCliente().getNombre(),
                        r.getCliente().getEmail(),
                        r.getFecha(),
                        r.getMesa().getNumeroMesa(),
                        r.getNumeroPersonas()
                ))
                .collect(Collectors.toList());
    }
}
