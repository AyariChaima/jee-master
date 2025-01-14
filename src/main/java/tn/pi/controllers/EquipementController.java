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




    /* partie l admin win ynjm yaaml kol chy

    // Display all equipment
    @GetMapping
    public String index(Model model) {
        List<Equipement> equipments = equipementRepository.findAll();
        model.addAttribute("equipments", equipments);
        return "equipement";
    }

    // Add new equipment
    @PostMapping("/add")
    public String addEquipement(@ModelAttribute Equipement equipement) {
        equipementRepository.save(equipement);
        return "redirect:/equipements";
    }

    // Display edit page
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Equipement> equipement = equipementRepository.findById(id);
        if (equipement.isPresent()) {
            model.addAttribute("equipement", equipement.get());
            return "edit-equipement";
        } else {
            return "redirect:/equipements";
        }
    }

    // Update equipment
    @PostMapping("/update/{id}")
    public String updateEquipement(@PathVariable Long id, @ModelAttribute("equipement") Equipement equipementDetails) {
        Optional<Equipement> equipementOptional = equipementRepository.findById(id);
        if (equipementOptional.isPresent()) {
            Equipement equipementToUpdate = equipementOptional.get();
            equipementToUpdate.setNomEquipement(equipementDetails.getNomEquipement());
            equipementToUpdate.setQuantite(equipementDetails.getQuantite());
            equipementToUpdate.setTypeEquipement(equipementDetails.getTypeEquipement());
            equipementRepository.save(equipementToUpdate);
            return "redirect:/equipements";
        } else {
            return "redirect:/equipements";
        }
    }

    // Delete equipment
    @PostMapping("/delete/{id}")
    public String deleteEquipement(@PathVariable Long id) {
        if (equipementRepository.existsById(id)) {
            equipementRepository.deleteById(id);
        }
        return "redirect:/equipements";
    }
     */
}
