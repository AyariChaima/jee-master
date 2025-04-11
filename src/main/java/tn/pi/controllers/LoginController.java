package tn.pi.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.UserEntity;
import tn.pi.services.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        try {
            UserEntity user = userService.loginUser(username, password);
            session.setAttribute("user", user); // Set the user in session
            if (!user.getRole().getRole().equals("ADMIN"))
            {return "redirect:/index";}
            else
            {return "redirect:/AdminPanel" ;}
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();  // Invalidate session on logout
        return "redirect:/login";  // Redirect to login page after logout
    }
}
