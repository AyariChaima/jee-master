package tn.pi.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.DemandeEquipement;
import tn.pi.entities.Equipement;
import tn.pi.entities.UserEntity;
import tn.pi.repositories.DemandeEquipementRepository;
import tn.pi.repositories.EquipementRepository;

import java.util.List;
import java.util.Date;

@Controller
@RequestMapping("/equipements")
public class EquipementController {

    @Autowired
    private EquipementRepository equipementRepository;

    @Autowired
    private DemandeEquipementRepository demandeEquipementRepository;

    // Display all equipments and demands for coach
    @GetMapping
    public String listEquipements(Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");

        if (user == null || !user.getRole().getRole().equals("COACH")) {
            return "redirect:/login"; // Ensure only coaches access this page
        }

        List<Equipement> equipements = equipementRepository.findAll();
        model.addAttribute("equipements", equipements);

        // Fetch the demands made by the logged-in coach
        List<DemandeEquipement> demandes = demandeEquipementRepository.findByCoach(user);
        model.addAttribute("demandes", demandes);

        return "equipement-list"; // Create a Thymeleaf template for listing equipment
    }

    // Handle equipment demand submission
    @PostMapping("/demande")
    public String createDemande(@RequestParam String nomEquipement,
                                @RequestParam String description,
                                HttpSession session) {
        // Get the logged-in user (coach)
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null || !user.getRole().getRole().equals("COACH")) {
            return "redirect:/login"; // Redirect to login if not authenticated or not a coach
        }

        // Create a new equipment demand
        DemandeEquipement demande = DemandeEquipement.builder()
                .nomEquipement(nomEquipement)
                .descriptionDemande(description)
                .dateDemande(new Date())
                .status("PENDING") // Default status
                .coach(user) // Associate the demand with the logged-in coach
                .build();

        // Save the demand in the database
        demandeEquipementRepository.save(demande);

        return "redirect:/equipements?success"; // Redirect with a success message
    }





}
