package pe.edu.utp.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.dto.HabitacionDTO;
import pe.edu.utp.modelo.Habitacion;
import pe.edu.utp.service.HabitacionService;

import java.util.List;

@Tag(name = "Habitacion", description = "Crud de habitacion")
@Controller
@RequestMapping("/admin/habitaciones")
public class HabitacionController {
    @Autowired
    private HabitacionService habitacionService;

    @GetMapping("/registrar")
    public String registro(Model model){
        model.addAttribute("habitacion", new HabitacionDTO());

        return "habitacion/registro";
    }

    @GetMapping
    public String lista(Model model){
        model.addAttribute("habitaciones", habitacionService.listarTodas());
        return "habitacion/lista";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute("habitacion") HabitacionDTO dto){
        habitacionService.guardar(dto);

        return "redirect:/habitaciones";
    }
}
