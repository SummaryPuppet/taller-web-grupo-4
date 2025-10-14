package pe.edu.utp.controllers.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.dto.HotelDTO;
import pe.edu.utp.modelo.Empleado;
import pe.edu.utp.modelo.Hotel;
import pe.edu.utp.service.HotelService;

import java.util.List;

@Tag(name = "Hoteles", description = "CRUD de hoteles")
@RestController
@RequestMapping("/api/hoteles")
public class HotelApiController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public Hotel crearHotel(@RequestBody HotelDTO hotel) {
        return hotelService.crearHotel(hotel);
    }

    @GetMapping
    public List<Hotel> listarHoteles() {
        return hotelService.listarHoteles();
    }

    @PostMapping("/{id}/empleado")
    public void asignarEmpleado(@PathVariable Long id, @RequestBody Empleado empleado) {
        hotelService.asignarEmpleado(id, empleado);
    }
}
