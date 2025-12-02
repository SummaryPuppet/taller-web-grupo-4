package pe.edu.utp.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.dto.ClienteDTO;
import pe.edu.utp.modelo.Cliente;
import pe.edu.utp.service.ClienteService;

import java.util.List;

@Tag(name = "Cliente", description = "Clientes")
@Controller
@RequestMapping("/admin/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    String lista(){
        return "cliente/lista";
    }

    @GetMapping("/registrar")
    String registro(){
        return "cliente/registro";
    }

    @PostMapping("/registrar")
    String registrar(@ModelAttribute ClienteDTO clienteDTO){
        clienteService.registrarCliente(clienteDTO);

        return "redirect:/admin/clientes";
    }
}
