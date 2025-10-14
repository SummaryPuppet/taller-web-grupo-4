package pe.edu.utp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservar")
public class ReservarController {
    @GetMapping
    public String reserva(){
        return "reservar";
    }
}
