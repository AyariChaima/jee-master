package tn.pi.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.Activite;
import tn.pi.entities.Participation;
import tn.pi.entities.UserEntity;
import tn.pi.repositories.ActiviteRepository;
import tn.pi.repositories.ParticipationRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/participations")
public class ParticipationController {

    @Autowired
    private ActiviteRepository activiteRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    // Display all activities with "Participate" and "Unparticipate" buttons
    @GetMapping
    public String listActivitiesForUsers(Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Ensure only logged-in users can access this page
        }

        List<Activite> activites = activiteRepository.findAll();

        // Map to store participation status for each activity
        Map<Long, Boolean> participationStatus = new HashMap<>();

        // Map to store the number of places left for each activity
        Map<Long, Integer> placesLeft = new HashMap<>();

        for (Activite activite : activites) {
            boolean isParticipating = participationRepository.findByUserAndActivite(user, activite).isPresent();
            participationStatus.put(activite.getIdActivite(), isParticipating);

            // Calculate the number of spots left
            int totalParticipants = participationRepository.countByActivite(activite); // Total users participating
            int maxParticipants = activite.getMaxParticipants(); // Max allowed participants
            int spotsLeft = maxParticipants - totalParticipants; // Calculate places left
            placesLeft.put(activite.getIdActivite(), Math.max(spotsLeft, 0)); // Ensure non-negative value

        }

        model.addAttribute("activites", activites);
        model.addAttribute("participationStatus", participationStatus);
        model.addAttribute("placesLeft", placesLeft);
        model.addAttribute("user", user);

        return "activity-list-user"; // Create this Thymeleaf page
    }

    // Participate in an activity
    @PostMapping("/participate/{id}")
    public String participateInActivity(@PathVariable Long id, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Ensure only logged-in users can access this
        }

        Optional<Activite> optionalActivite = activiteRepository.findById(id);
        if (optionalActivite.isPresent()) {
            Activite activite = optionalActivite.get();
            // Check if the user is already participating
            if (participationRepository.findByUserAndActivite(user, activite).isEmpty()) {
                Participation participation = Participation.builder()
                        .user(user)
                        .activite(activite)
                        .build();
                participationRepository.save(participation);
            }
        }

        return "redirect:/participations";
    }

    // Unparticipate from an activity
    @PostMapping("/unparticipate/{id}")
    public String unparticipateFromActivity(@PathVariable Long id, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Ensure only logged-in users can access this
        }

        Optional<Activite> optionalActivite = activiteRepository.findById(id);
        if (optionalActivite.isPresent()) {
            Activite activite = optionalActivite.get();
            Optional<Participation> participation = participationRepository.findByUserAndActivite(user, activite);
            participation.ifPresent(participationRepository::delete);
        }

        return "redirect:/participations";
    }
}
