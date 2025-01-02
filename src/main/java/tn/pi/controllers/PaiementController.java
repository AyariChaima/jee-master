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

    // Method to validate the payment
    @PostMapping("/valider")
    public String validerPaiement(@RequestParam Long offreId,
                                  @RequestParam Integer duration,
                                  @RequestParam String typePaiement,
                                  HttpSession session, Model model) {
        // Retrieve the offer details
        Offre offre = offreRepository.findById(offreId).orElse(null);
        if (offre == null) {
            return "error"; // Handle offer not found
        }

        // Retrieve the user from the session
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Redirect to login if user is not logged in
        }

        // Calculate the total amount
        double totalAmount = offre.getPrixUnitaire() * duration;

        // Create and save the payment record
        Paiement paiement = Paiement.builder()
                .datePaiement(new Date())
                .montantPaiement(totalAmount)
                .typePaiement(typePaiement)
                .duration(duration) // Add the duration here
                .user(user)  // Associate the current user
                .offre(offre) // Associate the offer
                .build();

        paiementRepository.save(paiement);

        // Show confirmation
        model.addAttribute("message", "Paiement effectué avec succès!");
        model.addAttribute("offre", offre);
        model.addAttribute("duration", duration);
        model.addAttribute("totalAmount", totalAmount);

        return "paiement-confirmation"; // The page where the confirmation is shown
    }


/* hedha l code l9dim
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
    }*/
}
