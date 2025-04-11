package tn.pi.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.Paiement;
import tn.pi.entities.Offre;
import tn.pi.entities.UserEntity;
import tn.pi.repositories.admin.AdminPaymentRepository;
import tn.pi.repositories.OffreRepository;
import tn.pi.repositories.admin.AdminUserRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/payments")
public class AdminPaymentController {

    @Autowired
    private AdminPaymentRepository adminPaymentRepository;

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private OffreRepository offreRepository;

    @GetMapping
    public String listPayments(Model model) {
        List<Paiement> payments = adminPaymentRepository.findAll();
        model.addAttribute("payments", payments);
        return "admin/payment-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("payment", new Paiement());
        model.addAttribute("users", adminUserRepository.findAll());
        model.addAttribute("offers", offreRepository.findAll());
        return "admin/add-payment";
    }

    @PostMapping("/add")
    public String addPayment(@ModelAttribute Paiement payment) {
        adminPaymentRepository.save(payment);
        return "redirect:/admin/payments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Paiement> paymentOpt = adminPaymentRepository.findById(id);
        if (paymentOpt.isPresent()) {
            model.addAttribute("payment", paymentOpt.get());
            model.addAttribute("users", adminUserRepository.findAll());
            model.addAttribute("offers", offreRepository.findAll());
            return "admin/edit-payment";
        }
        return "redirect:/admin/payments";
    }

    @PostMapping("/update/{id}")
    public String updatePayment(@PathVariable Long id, @ModelAttribute Paiement updatedPayment) {
        Optional<Paiement> paymentOpt = adminPaymentRepository.findById(id);
        if (paymentOpt.isPresent()) {
            Paiement payment = paymentOpt.get();
            payment.setMontantPaiement(updatedPayment.getMontantPaiement());
            payment.setDatePaiement(updatedPayment.getDatePaiement());
            payment.setTypePaiement(updatedPayment.getTypePaiement());
            payment.setDuration(updatedPayment.getDuration());
            payment.setUser(updatedPayment.getUser());
            payment.setOffre(updatedPayment.getOffre());
            adminPaymentRepository.save(payment);
        }
        return "redirect:/admin/payments";
    }

    @PostMapping("/delete/{id}")
    public String deletePayment(@PathVariable Long id) {
        adminPaymentRepository.deleteById(id);
        return "redirect:/admin/payments";
    }
}
