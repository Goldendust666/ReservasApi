package com.dwes.restaurante.Controller;

import com.dwes.restaurante.Entity.Mesa;
import com.dwes.restaurante.Repository.MesaRepository;
import com.dwes.restaurante.Service.MesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mesas")  // Prefijo para todos los endpoints
@Tag(name = "Mesas", description = "Gestión de mesas en el restaurante")  // Título y descripción en Swagger
public class MesaController {

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private MesaService mesaService;

    /** Listar todas las mesas */
    @Operation(summary = "Obtener todas las mesas", description = "Devuelve una lista con todas las mesas registradas en el restaurante.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de mesas obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<List<Mesa>> GetAllMesas() {
        List<Mesa> mesas = mesaRepository.findAll();
        return ResponseEntity.ok(mesas);
    }

    /** Insertar una nueva mesa */
    @Operation(summary = "Registrar una nueva mesa", description = "Agrega una nueva mesa a la base de datos.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Mesa creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping
    public ResponseEntity<Mesa> NewMesa(@RequestBody Mesa mesa) {
        Mesa saveMesa = mesaRepository.save(mesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveMesa);
    }

    /** Obtener una mesa por ID */
    @Operation(summary = "Obtener una mesa por ID", description = "Devuelve la mesa correspondiente al ID proporcionado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mesa encontrada"),
            @ApiResponse(responseCode = "404", description = "Mesa no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Mesa> getMesa(@PathVariable Long id) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la mesa con el ID: " + id));
        return ResponseEntity.ok(mesa);
    }

    /** Modificar una mesa */
    @Operation(summary = "Modificar una mesa", description = "Actualiza la información de una mesa existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mesa actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Mesa no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Mesa> modifyMesa(@PathVariable Long id, @RequestBody Mesa mesa) {
        Mesa existingMesa = mesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la mesa con el ID: " + id + " por lo que no se pudo modificar."));

        existingMesa.setNumeroMesa(mesa.getNumeroMesa());
        existingMesa.setDescripcion(mesa.getDescripcion());

        Mesa updatedMesa = mesaRepository.save(existingMesa);
        return ResponseEntity.ok(updatedMesa);
    }

    /** Borrar una mesa */
    @Operation(summary = "Eliminar una mesa", description = "Elimina una mesa de la base de datos.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Mesa eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Mesa no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMesa(@PathVariable Long id) {
        mesaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
