package tn.pi.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.Offre;
import tn.pi.repositories.admin.AdminOfferRepository;

import java.util.List;

@Controller
@RequestMapping("/admin/offres")
public class AdminOfferController {

    @Autowired
    private AdminOfferRepository offerRepository;

    @GetMapping
    public String listOffres(Model model) {
        List<Offre> offres = offerRepository.findAll();
        model.addAttribute("offres", offres);
        return "admin/offre-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("offre", new Offre());
        return "admin/offre-form";
    }

    @PostMapping("/save")
    public String saveOffre(@ModelAttribute Offre offre) {
        offerRepository.save(offre);
        return "redirect:/admin/offres";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Offre offre = offerRepository.findById(id).orElse(null);
        model.addAttribute("offre", offre);
        return "admin/offre-form";
    }

    @PostMapping("/delete/{id}")
    public String deleteOffre(@PathVariable Long id) {
        offerRepository.deleteById(id);
        return "redirect:/admin/offres";
    }
}
