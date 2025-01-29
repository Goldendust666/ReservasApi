package com.dwes.restaurante.Controller;

import com.dwes.restaurante.Entity.Mesa;
import com.dwes.restaurante.Repository.ReservaRepository;
import com.dwes.restaurante.Service.MesaService;
import com.dwes.restaurante.Service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservaController {
     @Autowired
     private ReservaService reservaService;
     @Autowired
     private ReservaRepository reservaRepository;


    /**borrar*/
    @DeleteMapping("/reservas/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reservas/{id}")
    public ResponseEntity<Void> deleteReserva2(@PathVariable Long id) {
        reservaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
