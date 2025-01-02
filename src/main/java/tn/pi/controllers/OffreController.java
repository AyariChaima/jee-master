package tn.pi.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.pi.entities.Offre;
import tn.pi.repositories.OffreRepository;

@Controller
public class OffreController {
    @Autowired
    private OffreRepository offreRepository;

    @GetMapping("/offre/choisir/{id}")
        public String choisirOffre(@PathVariable Long id, Model model) {
        // Retrieve the offer from the database using the id
        Offre offre = offreRepository.findById(id).orElse(null);
        if (offre == null) {
            return "error"; // Handle offer not found
        }
        model.addAttribute("offre", offre);
        return "choisir-offre"; // Offer selection page
    }
}
