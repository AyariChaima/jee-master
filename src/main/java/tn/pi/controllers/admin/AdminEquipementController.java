package tn.pi.controllers.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.DemandeEquipement;
import tn.pi.entities.Equipement;
import tn.pi.repositories.DemandeEquipementRepository;
import tn.pi.repositories.admin.AdminEquipementRepository;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/equipements")
public class AdminEquipementController {

    @Autowired
    private AdminEquipementRepository adminEquipementRepository;

    @Autowired
    private DemandeEquipementRepository demandeEquipementRepository;

    @GetMapping
    public String listEquipments(Model model) {
        List<Equipement> equipments = adminEquipementRepository.findAll();
        List<DemandeEquipement> demandes = demandeEquipementRepository.findAll();
        model.addAttribute("equipments", equipments);
        model.addAttribute("demandes", demandes);
        return "admin/equipements";
    }

    // Add new equipment
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("equipment", new Equipement());
        return "admin/add-equipement";
    }

    @PostMapping("/add")
    public String addEquipement(@ModelAttribute Equipement equipement) {
        adminEquipementRepository.save(equipement);
        return "redirect:/admin/equipements";
    }

    // Display edit page
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Equipement> equipement = adminEquipementRepository.findById(id);
        if (equipement.isPresent()) {
            model.addAttribute("equipement", equipement.get());
            return "admin/edit-equipement";
        } else {
            return "redirect:/Admin/equipements";
        }
    }

    // Update equipment
    @PostMapping("/update/{id}")
    public String updateEquipement(@PathVariable Long id, @ModelAttribute("equipement") Equipement equipementDetails) {
        Optional<Equipement> equipementOptional = adminEquipementRepository.findById(id);
        if (equipementOptional.isPresent()) {
            Equipement equipementToUpdate = equipementOptional.get();
            equipementToUpdate.setNomEquipement(equipementDetails.getNomEquipement());
            equipementToUpdate.setQuantite(equipementDetails.getQuantite());
            equipementToUpdate.setTypeEquipement(equipementDetails.getTypeEquipement());
            adminEquipementRepository.save(equipementToUpdate);
            return "redirect:/admin/equipements";
        } else {
            return "redirect:/admin/equipements";
        }
    }

    // Delete equipment
    @PostMapping("/delete/{id}")
    public String deleteEquipement(@PathVariable Long id) {
        if (adminEquipementRepository.existsById(id)) {
            adminEquipementRepository.deleteById(id);
        }
        return "redirect:/admin/equipements";
    }

    // This is for the demands
    @PostMapping("/demandes/approve/{id}")
    public String approveDemande(@PathVariable Long id) {
        Optional<DemandeEquipement> demande = demandeEquipementRepository.findById(id);
        demande.ifPresent(d -> {
            d.setStatus("APPROVED");
            demandeEquipementRepository.save(d);
        });
        return "redirect:/admin/equipements";
    }
    @PostMapping("/demandes/reject/{id}")
    public String rejectDemande(@PathVariable Long id) {
        Optional<DemandeEquipement> demande = demandeEquipementRepository.findById(id);
        demande.ifPresent(d -> {
            d.setStatus("REJECTED");
            demandeEquipementRepository.save(d);
        });
        return "redirect:/admin/equipements";
    }

    @PostMapping("/demandes/delete/{id}")
    public String deleteDemande(@PathVariable Long id) {
        demandeEquipementRepository.deleteById(id);
        return "redirect:/admin/equipements";
    }

}
