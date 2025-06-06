package tn.pi.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.UserEntity;
import tn.pi.services.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;


    @GetMapping
    public String showProfilePage(HttpSession session, Model model) {
        UserEntity sessionUser = (UserEntity) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/login";
        }

        // Refresh user data from the database
        UserEntity user = userService.findById(sessionUser.getId_user());
        session.setAttribute("user", user);

        model.addAttribute("user", user);

        var paiements = user.getPaiements();
        if (paiements != null && !paiements.isEmpty()) {
            var lastPaiement = paiements.get(paiements.size() - 1);
            model.addAttribute("offer", lastPaiement.getOffre());
            model.addAttribute("duration", lastPaiement.getDuration());
            model.addAttribute("paymentDate", lastPaiement.getDatePaiement());
        } else {
            model.addAttribute("offer", null); // No offer found
        }

        return "profile";
    }


    @PostMapping("/update")
    public String updateProfile(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam String username,
                                @RequestParam String email,
                                @RequestParam int age,
                                @RequestParam String phone,
                                @RequestParam String emergencyPhone,
                                @RequestParam(required = false) String speciality,
                                HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setAge(age);
        user.setPhone_number(phone);
        user.setEmergency_phone_number(emergencyPhone);

        if (user.getRole().getRole().equals("COACH")) {
            user.setSpeciality(speciality);
        }

        userService.updateUser(user);
        session.setAttribute("user", user);
        return "redirect:/profile";
    }
}
