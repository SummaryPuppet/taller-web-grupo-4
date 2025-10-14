package pe.edu.utp.controllers.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.dto.ReservaDTO;
import pe.edu.utp.modelo.Reserva;
import pe.edu.utp.service.ReservaService;

import java.util.List;

@Tag(name = "Reservas", description = "CRUD de reservas")
@RestController
@RequestMapping("/api/reservas")
public class ReservaApiController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> crear(@RequestBody ReservaDTO reserva) {
        try {
            Reserva reservaResponse = reservaService.crearReservaDTO(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(reservaResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public List<Reserva> listar() {
        return reservaService.listarReservas();
    }

    @GetMapping("/activa")
    public List<Reserva> listarActivas() {
        return reservaService.listarReservasActivas();
    }

    @DeleteMapping("/{id}")
    public void cancelar(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
    }
}
