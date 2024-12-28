package tn.pi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {
    // Mapping pour la page d'accueil
    @GetMapping("/home")
    public String home() {
        return "home"; // Renvoie vers le template `index.html`
    }
}
