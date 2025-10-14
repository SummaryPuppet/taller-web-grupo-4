package pe.edu.utp.controllers.api;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.dto.EmpleadoDTO;
import pe.edu.utp.modelo.Empleado;
import pe.edu.utp.service.EmpleadoService;


import java.util.List;

@Tag(name = "Empleados", description = "CRUD de empleados")
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoApiController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping
    public Empleado registrarEmpleado(@RequestBody EmpleadoDTO empleado) {
        return empleadoService.registrarEmpleado(empleado);
    }

    @GetMapping("/{dni}")
    public Empleado obtenerPorDni(@PathVariable String dni) {
        return empleadoService.obtenerEmpleadoPorDni(dni);
    }

    @GetMapping
    public List<Empleado> listar() {
        return empleadoService.listarEmpleados();
    }
}
