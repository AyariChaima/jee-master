package tn.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.Equipement;
import tn.pi.repositories.EquipementRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/equipements")
public class EquipementController {

    private final EquipementRepository equipementRepository;

    @Autowired
    public EquipementController(EquipementRepository equipementRepository) {
        this.equipementRepository = equipementRepository;
    }

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
}
