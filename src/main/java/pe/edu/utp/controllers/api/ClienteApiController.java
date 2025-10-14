package pe.edu.utp.controllers.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.dto.ClienteDTO;
import pe.edu.utp.modelo.Cliente;
import pe.edu.utp.service.ClienteService;

import java.util.List;

@Tag(name = "Cliente", description = "CRUD clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteApiController {
    @Autowired
    private ClienteService service;

    @PostMapping
    public Cliente registrar(@RequestBody ClienteDTO cliente) {
        return service.registrarCliente(cliente);
    }

    @GetMapping("/{dni}")
    public Cliente obtenerPorDni(@PathVariable String dni) {
        return service.obtenerClientePorDni(dni);
    }

    @GetMapping
    public List<Cliente> listarTodos() {
        return service.listarClientes();
    }
}
