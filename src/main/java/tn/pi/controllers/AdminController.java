package tn.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.DemandeEquipement;
import tn.pi.repositories.DemandeEquipementRepository;

import java.util.List;

@Controller
@RequestMapping("/admin/demande")
public class AdminController {
/* hedha bch ychouf les demande w yapprouvehom */
    @Autowired
    private DemandeEquipementRepository demandeEquipementRepository;

    // Display all equipment demands
    @GetMapping
    public String listDemands(Model model) {
        List<DemandeEquipement> demandes = demandeEquipementRepository.findAll();
        model.addAttribute("demandes", demandes);
        return "admin-demande"; // Create a Thymeleaf template for displaying demands
    }

    // Approve a demand
    @PostMapping("/approve/{id}")
    public String approveDemand(@PathVariable Long id) {
        DemandeEquipement demande = demandeEquipementRepository.findById(id).orElse(null);
        if (demande != null) {
            demande.setStatus("APPROVED");
            demandeEquipementRepository.save(demande);
        }
        return "redirect:/admin-demande";
    }

    // Reject a demand
    @PostMapping("/reject/{id}")
    public String rejectDemand(@PathVariable Long id) {
        DemandeEquipement demande = demandeEquipementRepository.findById(id).orElse(null);
        if (demande != null) {
            demande.setStatus("REJECTED");
            demandeEquipementRepository.save(demande);
        }
        return "redirect:/admin-demande";
    }
}
