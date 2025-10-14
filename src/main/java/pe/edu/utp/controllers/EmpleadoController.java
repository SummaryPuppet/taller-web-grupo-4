package pe.edu.utp.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.dto.EmpleadoDTO;
import pe.edu.utp.modelo.Empleado;
import pe.edu.utp.service.EmpleadoService;


import java.util.List;

@Tag(name = "Empleados", description = "CRUD de empleados")
@Controller
@RequestMapping("/admin/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    String lista(){
        return "empleado/lista";
    }

    @GetMapping("/registrar")
    String registro (Model model){
        model.addAttribute("empleado", new EmpleadoDTO());

        return "empleado/registro";
    }

    @PostMapping("/registrar")
    String registro(@ModelAttribute("empleado") EmpleadoDTO dto){
        empleadoService.registrarEmpleado(dto);
        return "redirect:/empleados";
    }
}