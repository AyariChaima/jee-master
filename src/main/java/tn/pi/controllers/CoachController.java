package tn.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.Coach;
import tn.pi.repositories.CoachRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/coaches")
public class CoachController {

    private final CoachRepository coachRepository;

    @Autowired
  public CoachController(CoachRepository coachRepository){
        this.coachRepository=coachRepository;
    }

    // Display all coaches on the HTML page
    @GetMapping
    public String index(Model model) {
        List<Coach> coaches = coachRepository.findAll();
        model.addAttribute("coaches", coaches);
        return "coach";
    }

    // Add a new coach
    @PostMapping("/add")
    public String addCoach(@ModelAttribute Coach coach) {
        coachRepository.save(coach);
        return "redirect:/coaches";
    }

    // Display edit page
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Coach> coach = coachRepository.findById(id);
        if (coach.isPresent()) {
            model.addAttribute("coach", coach.get());
            return "edit-coach";
        } else {
            return "redirect:/coaches"; // Redirect to coach list if not found
        }
    }

    // Update coach
    @PostMapping("/update/{id}")
    public String updateCoach(@PathVariable Long id, @ModelAttribute Coach coachDetails) {
        Optional<Coach> coachOptional = coachRepository.findById(id);
        if (coachOptional.isPresent()) {
            Coach coachToUpdate = coachOptional.get();
            coachToUpdate.setNom_coach(coachDetails.getNom_coach());
            coachToUpdate.setPrenom_coach(coachDetails.getPrenom_coach());
            coachToUpdate.setEmail(coachDetails.getEmail());
            coachToUpdate.setSpecialite(coachDetails.getSpecialite());

            coachRepository.save(coachToUpdate);
            return "redirect:/coaches";
        } else {
            return "redirect:/coaches";
        }
    }

    // Delete coach
    @PostMapping("/delete/{id}")
    public String deleteCoach(@PathVariable Long id) {
        if (coachRepository.existsById(id)) {
            coachRepository.deleteById(id);
        }
        return "redirect:/coaches";
    }
}
