package tn.pi.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.Offre;
import tn.pi.entities.Paiement;
import tn.pi.entities.UserEntity;
import tn.pi.repositories.OffreRepository;
import tn.pi.repositories.PaiementRepository;

import java.util.Date;

@Controller
@RequestMapping("/paiements")
public class PaiementController {

    @Autowired
    private PaiementRepository paiementRepository;

    @Autowired
    private OffreRepository offreRepository;

    @PostMapping("/valider")
    public String validerPaiement(@RequestParam Long offreId,
                                  @RequestParam Integer duration,
                                  @RequestParam String typePaiement,
                                  HttpSession session, Model model) {

        Offre offre = offreRepository.findById(offreId).orElse(null);
        if (offre == null) {
            return "error"; // Handle offer not found
        }


        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Redirect to login if user is not logged in
        }


        double totalAmount = offre.getPrixUnitaire() * duration;


        Paiement paiement = Paiement.builder()
                .datePaiement(new Date())
                .montantPaiement(totalAmount)
                .typePaiement(typePaiement)
                .duration(duration)
                .user(user)
                .offre(offre)
                .build();

        paiementRepository.save(paiement);

        model.addAttribute("message", "Paiement effectué avec succès!");
        model.addAttribute("offre", offre);
        model.addAttribute("duration", duration);
        model.addAttribute("totalAmount", totalAmount);

        return "redirect:/profile";
    }
}
