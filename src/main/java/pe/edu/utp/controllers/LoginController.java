package pe.edu.utp.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "Login", description = "Manejo de login y registro")
@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(){
        return "redirect:/reservar";
    }

    @GetMapping("/registro")
    public String registro(){
        return "registro";
    }


}
