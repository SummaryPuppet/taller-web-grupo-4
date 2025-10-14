package pe.edu.utp.controllers.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.dto.HabitacionDTO;
import pe.edu.utp.modelo.Habitacion;
import pe.edu.utp.service.HabitacionService;

import java.util.List;


@Tag(name = "Habitacion", description = "Crud de habitacion")
@RestController
@RequestMapping("/api/habitacion")
public class HabitacionApiController {
    @Autowired
    private HabitacionService service;

    @GetMapping
    public List<Habitacion> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{numero}")
    public ResponseEntity<Habitacion> obtenerPorNumero(@PathVariable String numero) {
        return service.buscarPorNumero(numero)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Habitacion crear(@RequestBody HabitacionDTO habitacion) {
        return service.guardar(habitacion);
    }

    @PutMapping("/{numero}/reservar")
    public Habitacion reservar(@PathVariable String numero) {
        return service.reservar(numero);
    }

    @PutMapping("/{numero}/liberar")
    public Habitacion liberar(@PathVariable String numero) {
        return service.liberar(numero);
    }

    @DeleteMapping("/{numero}")
    public void eliminar(@PathVariable Long numero) {
        service.eliminar(numero);
    }
}
