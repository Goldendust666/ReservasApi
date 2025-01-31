package com.dwes.restaurante.Controller;

import com.dwes.restaurante.Entity.Cliente;
import com.dwes.restaurante.Repository.ClienteRepository;
import com.dwes.restaurante.Service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Gestión de clientes") // Añade un nombre y descripción al controlador
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    /** Listar todos los clientes */
    @Operation(summary = "Obtener todos los clientes", description = "Devuelve una lista con todos los clientes registrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de clientes devuelta correctamente")
    })
    @GetMapping
    public ResponseEntity<List<Cliente>> GetAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.ok(clientes);
    }

    /** Insertar un cliente */
    @Operation(summary = "Registrar un nuevo cliente", description = "Inserta un nuevo cliente en la base de datos.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<Cliente> NewEmpleado(@Valid @RequestBody Cliente cliente) {
        Cliente saveClient = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveClient);
    }

    /** Obtener un cliente por ID */
    @Operation(summary = "Obtener cliente por ID", description = "Devuelve un cliente según el ID proporcionado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente devuelto correctamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el cliente con el ID: " + id));
        return ResponseEntity.ok(cliente);
    }

    /** Modificar un cliente */
    @Operation(summary = "Modificar un cliente", description = "Actualiza los datos de un cliente existente según el ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> modifyCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        Cliente existingCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el cliente con el ID: " + id + " por lo que no se pudo modificar."));

        existingCliente.setNombre(cliente.getNombre());
        existingCliente.setTelefono(cliente.getTelefono());
        existingCliente.setEmail(cliente.getEmail());

        Cliente updatedCliente = clienteRepository.save(existingCliente);

        return ResponseEntity.ok(updatedCliente);
    }

    /** Borrar un cliente */
    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente de la base de datos según el ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cliente eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
