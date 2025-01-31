package com.dwes.restaurante.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReservaDTO {
    private String nombre;        // Nombre del cliente
    private String email;         // Email del cliente
    private LocalDateTime fechaReserva; // Fecha y hora de la reserva
    private int numeroMesa;       // Número de la mesa
    private int numeroPersonas;   // Número de personas en la reserva
}
