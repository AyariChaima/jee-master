package tn.pi.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tn.pi.repositories.PaiementRepository;

@Controller
@RequestMapping("/admin/payments")
public class AdminPaymentController {

    @Autowired
    private PaiementRepository paymentRepository;

    @GetMapping
    public String listPayments(Model model) {
        model.addAttribute("payments", paymentRepository.findAll());
        return "admin/payments";
    }
}
