package tn.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.Paiement;
import tn.pi.repositories.PaiementRepository;

import java.util.List;

@Controller
@RequestMapping("/paiements")
public class PaiementController {

    @Autowired
    private PaiementRepository paiementRepository;

    @GetMapping
    public String listPaiements(Model model) {
        List<Paiement> paiements = paiementRepository.findAll();
        model.addAttribute("paiements", paiements);
        return "paiement";
    }

    @GetMapping("/ajout")
    public String showAddForm(Model model) {
        model.addAttribute("paiement", new Paiement());
        return "ajout-paiement";
    }

    @PostMapping("/add")
    public String addPaiement(@ModelAttribute Paiement paiement) {
        paiementRepository.save(paiement);
        return "redirect:/paiements";
    }



    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Paiement paiement = paiementRepository.findById(id).orElse(null);
        if (paiement != null) {
            model.addAttribute("paiement", paiement);
            return "edit-paiement";
        }
        return "redirect:/paiements";
    }



    @PostMapping("/update/{id}")
    public String updatePaiement(@PathVariable Long id, @ModelAttribute Paiement paiementDetails) {
        Paiement paiement = paiementRepository.findById(id).orElse(null);
        if (paiement == null) {
            // Retourne une erreur si l'entité Paiement n'est pas trouvée
            return "redirect:/paiements";
        }
        paiement.setDatePaiement(paiementDetails.getDatePaiement());
        paiement.setTypePaiement(paiementDetails.getTypePaiement());
        paiement.setMontantPaiement(paiementDetails.getMontantPaiement());
        paiementRepository.save(paiement);
        return "redirect:/paiements";
    }


    @PostMapping("/delete/{id}")
    public String deletePaiement(@PathVariable Long id) {
        paiementRepository.deleteById(id);
        return "redirect:/paiements";
    }
}
