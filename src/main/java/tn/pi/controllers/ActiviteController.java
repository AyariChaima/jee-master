package tn.pi.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.Activite;
import tn.pi.repositories.ActiviteRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/activites")
public class ActiviteController {

    private final ActiviteRepository activiteRepository;

    @Autowired
    public ActiviteController(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }

    // Display all activities on the HTML page
    @GetMapping
    public String index(Model model) {
        List<Activite> activites = activiteRepository.findAll();
        model.addAttribute("activites", activites);
        return "activite";
    }

    // Add a new activity
    @PostMapping("/add")
    public String addActivite(@ModelAttribute Activite activite) {
        activiteRepository.save(activite);
        return "redirect:/activites";
    }


    // Display edit page
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Activite> activite = activiteRepository.findById(id);
        if (activite.isPresent()) {
            model.addAttribute("activite", activite.get());
            return "edit-activite";
        } else {
            return "redirect:/activites"; // Redirect to activity list if not found
        }
    }

    // Update activity
    @PostMapping("/update/{id}")
        public String updateActivite(@PathVariable Long id, @ModelAttribute Activite activiteDetails) {
        Optional<Activite> activiteOptional = activiteRepository.findById(id);
        if (activiteOptional.isPresent()) {
            Activite activiteToUpdate = activiteOptional.get();
            activiteToUpdate.setNomActivite(activiteDetails.getNomActivite());
            activiteToUpdate.setDateDebutActivite(activiteDetails.getDateDebutActivite());
            activiteToUpdate.setDateFinActivite(activiteDetails.getDateFinActivite());

            activiteRepository.save(activiteToUpdate);
            return "redirect:/activites";
        } else {
            return "redirect:/activites";
        }
    }


    // Delete activity
    @PostMapping("/delete/{id}")
    public String deleteActivite(@PathVariable long id) {
        if (activiteRepository.existsById(id)) {
            activiteRepository.deleteById(id);
        }
        return "redirect:/activites";
    }

}
