package tn.pi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class IndexController {
    // Mapping pour la page d'accueil
    @GetMapping("/index")
    public String index() {
        return "index"; // Renvoie vers le template `index.html`
    }
}
