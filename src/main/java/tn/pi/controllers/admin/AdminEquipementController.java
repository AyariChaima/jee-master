package tn.pi.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.Equipement;
import tn.pi.repositories.EquipementRepository;

@Controller
@RequestMapping("/admin/equipements")
public class AdminEquipementController {

    @Autowired
    private EquipementRepository equipementRepository;

    @GetMapping
    public String listEquipments(Model model) {
        model.addAttribute("equipements", equipementRepository.findAll());
        return "admin/equipements";
    }

    @PostMapping("/add")
    public String addEquipment(@ModelAttribute Equipement equipement) {
        equipementRepository.save(equipement);
        return "redirect:/admin/equipements";
    }

    @PostMapping("/delete/{id}")
    public String deleteEquipment(@PathVariable Long id) {
        equipementRepository.deleteById(id);
        return "redirect:/admin/equipements";
    }
}
