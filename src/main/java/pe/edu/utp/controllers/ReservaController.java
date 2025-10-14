package pe.edu.utp.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.dto.ReservaDTO;
import pe.edu.utp.service.ReservaService;

@Tag(name = "Reservas", description = "CRUD de reservas")
@Controller
@RequestMapping("/admin/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public String lista(Model model){
        model.addAttribute("reservas", reservaService.listarReservas());
        return "reserva/index";
    }

}