package pe.edu.utp.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.dto.HotelDTO;
import pe.edu.utp.modelo.Hotel;
import pe.edu.utp.service.EmpleadoService;
import pe.edu.utp.service.HotelService;

import java.util.List;

@Tag(name = "Hoteles", description = "Hoteles")
@Controller
@RequestMapping("/admin/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private EmpleadoService empleadoService;

    // (1) Formulario de registro
    @GetMapping("/registrar")
    public String formNuevo(Model model) {
        model.addAttribute("hotel", HotelDTO.builder().build());
        return "hotel/registro";
    }

    @PostMapping("/registrar")
    public String registro(@ModelAttribute("hotel") HotelDTO dto) {
        hotelService.crearHotel(dto);
        return "redirect:/hotel";
    }

    // (2) Lista de hoteles
    @GetMapping
    public String lista(Model model) {
        List<Hotel> hoteles = hotelService.listarHoteles();
        model.addAttribute("hoteles", hoteles);
        return "hotel/lista";
    }

    // (3) Detalle de hotel
    //@GetMapping("/{id}")
    //public String detalle(@PathVariable Long id, Model model) {
    //    Hotel hotel = hotelService.buscarHotelPorId(id);
    //    model.addAttribute("hotel", hotel);
    //    return "hotel/hotel-detalle";
    //}

    // (4) Asignar empleado a hotel (GET muestra formulario, POST asigna)
    //@GetMapping("/asignar-empleado")
    //public String formAsignarEmpleado(Model model) {
    //    model.addAttribute("hoteles", hotelService.listarHoteles());
    //    model.addAttribute("empleados", empleadoService.listarEmpleados());
    //    return "hotel/hotel-assignar-empleado";
    //}

}