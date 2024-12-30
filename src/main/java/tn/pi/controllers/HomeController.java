package tn.pi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Mapping pour la page d'accueil
    @GetMapping("/home")
    public String home() {
        return "home"; // Renvoie vers le template `index.html`
    }
}
