package tn.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.Adherent;
import tn.pi.repositories.AdherentRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/adherents")
public class AdherentController {

    private final AdherentRepository adherentRepository;

    @Autowired
    public AdherentController(AdherentRepository adherentRepository) {
        this.adherentRepository = adherentRepository;
    }

    // Display all adherents on the HTML page
    @GetMapping
    public String index(Model model) {
        List<Adherent> adherents = adherentRepository.findAll();
        model.addAttribute("adherents", adherents);
        return "adherent";
    }

    // Add a new adherent
    @PostMapping("/add")
    public String addAdherent(@ModelAttribute Adherent adherent) {
        adherentRepository.save(adherent);
        return "redirect:/adherents";
    }

    // Display edit page
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Adherent> adherent = adherentRepository.findById(id);
        if (adherent.isPresent()) {
            model.addAttribute("adherent", adherent.get());
            return "edit-adherent";
        } else {
            return "redirect:/adherents"; // Redirect to adherent list if not found
        }
    }

    // Update adherent
    @PostMapping("/update/{id}")
    public String updateAdherent(@PathVariable Long id, @ModelAttribute("adherent") Adherent adherentDetails) {
        Optional<Adherent> adherentOptional = adherentRepository.findById(id);
        if (adherentOptional.isPresent()) {
            Adherent adherentToUpdate = adherentOptional.get();
            adherentToUpdate.setNom_adherent(adherentDetails.getNom_adherent());
            adherentToUpdate.setPrenom_adherent(adherentDetails.getPrenom_adherent());
            adherentToUpdate.setAge(adherentDetails.getAge());
            adherentToUpdate.setEmail(adherentDetails.getEmail());
            adherentToUpdate.setDate_inscription(adherentDetails.getDate_inscription());

            adherentRepository.save(adherentToUpdate); // Sauvegarde des modifications
            return "redirect:/adherents"; // Redirige vers la liste après mise à jour
        } else {
            return "redirect:/adherents";
        }
    }


    // Delete adherent
    @GetMapping("/delete/{id}")
    public String deleteAdherent(@PathVariable Long id) {
        if (adherentRepository.existsById(id)) {
            adherentRepository.deleteById(id);
        }
        return "redirect:/adherents"; // Redirect to adherent list after deletion
    }
}
