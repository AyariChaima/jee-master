package tn.pi.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.Coach;
import tn.pi.entities.UserEntity;
import tn.pi.repositories.admin.AdminCoachRepository;

import java.util.List;

@Controller
@RequestMapping("/admin/coaches")
public class AdminCoachController {

    @Autowired
    private AdminCoachRepository adminCoachRepository;

    @GetMapping
    public String listCoaches(Model model) {
        List<UserEntity> coaches = adminCoachRepository.findAllCoaches();
        model.addAttribute("coaches", coaches);
        return "admin/coaches";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "admin/add-coach";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute UserEntity user) {
        adminCoachRepository.save(user);
        return "redirect:admin/coaches";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        UserEntity coach = adminCoachRepository.findById(id).orElse(null);
        if (coach != null) {
            model.addAttribute("coach", coach);
            return "admin/edit-coach";
        }
        return "redirect:/admin/coaches";
    }

    @PostMapping("/update/{id}")
    public String updateCoach(@PathVariable Long id, @ModelAttribute UserEntity coachDetails) {
        UserEntity coach = adminCoachRepository.findById(id).orElse(null);
        if (coach != null) {
            coach.setFirst_name(coachDetails.getFirst_name());
            coach.setLast_name(coachDetails.getLast_name());
            coach.setEmail(coachDetails.getEmail());
            coach.setSpeciality(coachDetails.getSpeciality());
            adminCoachRepository.save(coach);
        }
        return "redirect:/admin/coaches";
    }

    @PostMapping("/delete/{id}")
    public String deleteCoach(@PathVariable Long id) {
        UserEntity coach = adminCoachRepository.findById(id).orElse(null);
        if (coach != null) {
            adminCoachRepository.deleteById(id);
        }
        return "redirect:/admin/coaches";
    }
}