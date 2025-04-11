package tn.pi.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.Activite;
import tn.pi.entities.Equipement;
import tn.pi.entities.UserEntity;
import tn.pi.repositories.*;
import java.util.List;

@Controller
@RequestMapping("/admin/activities")
public class AdminActivityController {

    @Autowired
    private ActiviteRepository activiteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EquipementRepository equipementRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @GetMapping
    public String listActivities(Model model) {
        List<Activite> activities = activiteRepository.findAll();
        model.addAttribute("activities", activities);
        return "admin/activity-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("activity", new Activite());
        model.addAttribute("coaches", userRepository.findAll());
        model.addAttribute("equipements", equipementRepository.findAll());
        return "admin/activity-form";
    }

    @PostMapping("/save")
    public String saveActivity(@ModelAttribute Activite activity) {
        activiteRepository.save(activity);
        return "redirect:/admin/activities";
    }

    @GetMapping("/edit/{id}")
    public String editActivity(@PathVariable Long id, Model model) {
        Activite activity = activiteRepository.findById(id).orElseThrow();
        model.addAttribute("activity", activity);
        model.addAttribute("coaches", userRepository.findAll());
        model.addAttribute("equipements", equipementRepository.findAll());
        return "admin/activity-form";
    }

    @PostMapping("/update")
    public String updateActivity(@ModelAttribute Activite activity) {
        activiteRepository.save(activity);
        return "redirect:/admin/activities";
    }

    @PostMapping("/delete/{id}")
    public String deleteActivity(@PathVariable Long id) {
        activiteRepository.deleteById(id);
        return "redirect:/admin/activities";
    }
}
