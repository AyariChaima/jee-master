package tn.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.services.UserService;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String username,
                               @RequestParam String password,
                               @RequestParam Integer age,
                               @RequestParam String email,
                               @RequestParam String phoneNumber,
                               @RequestParam String emergencyPhoneNumber,
                               @RequestParam String dateOfInscription,
                               @RequestParam String role,
                               @RequestParam(required = false) String speciality,
                               Model model) {
        try {
            userService.registerUser(firstName, lastName, username, password, age, email, phoneNumber, emergencyPhoneNumber, dateOfInscription, role, speciality);
            model.addAttribute("success", "Registration successful!");
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "registration";
        }
    }
}
