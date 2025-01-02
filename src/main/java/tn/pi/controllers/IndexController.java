package tn.pi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tn.pi.entities.Offre;
import tn.pi.repositories.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private OffreRepository offreRepository;

    // Mapping pour la page d'accueil
    @GetMapping("/index")
    public String index(Model model) {
        List<Offre> offers = offreRepository.findAll();

        // Add the offers list to the model
        model.addAttribute("offers", offers);


        return "index2"; // Renvoie vers le template `index.html`
    }
}
