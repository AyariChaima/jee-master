package tn.pi.controllers;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import tn.pi.entities.UserEntity;

@Controller
public class AdminPanelController {

    // Mapping pour la page d'accueil
    @GetMapping("/AdminPanel")
    public String home(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Redirect to login if not logged in
        }
        return "AdminPanel";
    }
}
