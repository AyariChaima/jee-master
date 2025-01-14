package tn.pi.controllers;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.Activite;
import tn.pi.entities.Equipement;
import tn.pi.entities.UserEntity;
import tn.pi.repositories.ActiviteRepository;
import tn.pi.repositories.EquipementRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/activites")
public class ActiviteController {

    @Autowired
    private ActiviteRepository activiteRepository;

    @Autowired
    private EquipementRepository equipementRepository;

    @GetMapping
    public String listActivites(Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null || !user.getRole().getRole().equals("COACH")) {
            return "redirect:/login"; // Restrict access to coaches only
        }

        List<Activite> activites = activiteRepository.findByCoachId(user.getId_user());
        model.addAttribute("activites", activites);
        System.out.println("dkhalet lel controller lel list");
        return "activite-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null || !user.getRole().getRole().equals("COACH")) {
            return "redirect:/login";
        }
        System.out.println("dkhalet lel controller lel add");
        List<Equipement> equipements = equipementRepository.findAll();
        model.addAttribute("equipements", equipements); // List of available equipment
        return "add-activite";
    }

    @PostMapping("/add")
    public String addActivite(@RequestParam String nomActivite,
                              @RequestParam String dateDebut,
                              @RequestParam String dateFin,
                              @RequestParam(required = false) List<Long> equipements,
                              @RequestParam String salle,
                              @RequestParam int maxParticipants,
                              HttpSession session) {


        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null || !user.getRole().getRole().equals("COACH")) {
            return "redirect:/login";
        }

        List<Equipement> equipementList = equipementRepository.findAllById(equipements);

        Activite activite = Activite.builder()
                .nomActivite(nomActivite)
                .dateDebutActivite(LocalDateTime.parse(dateDebut))
                .dateFinActivite(LocalDateTime.parse(dateFin))
                .salle(salle)
                .maxParticipants(maxParticipants)
                .equipements(equipementList)
                .coach(user)
                .build();

        activiteRepository.save(activite);
        return "redirect:/activites";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null || !user.getRole().getRole().equals("COACH")) {
            return "redirect:/login";
        }

        Optional<Activite> optionalActivite = activiteRepository.findById(id);
        if (optionalActivite.isEmpty() || !optionalActivite.get().getCoach().getId_user().equals(user.getId_user())) {
            return "redirect:/activites"; // Ensure the coach owns the activity
        }

        List<Equipement> equipements = equipementRepository.findAll();
        model.addAttribute("activite", optionalActivite.get());
        model.addAttribute("equipements", equipements);
        return "edit-activite"; // Create this Thymeleaf page for editing
    }

    @PostMapping("/update/{id}")
    public String updateActivite(@PathVariable Long id,
                                 @RequestParam String nomActivite,
                                 @RequestParam String dateDebut,
                                 @RequestParam String dateFin,
                                 @RequestParam(required = false) List<Long> equipements,
                                 @RequestParam String salle,
                                 @RequestParam int maxParticipants,
                                 HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null || !user.getRole().getRole().equals("COACH")) {
            return "redirect:/login";
        }

        Optional<Activite> optionalActivite = activiteRepository.findById(id);
        if (optionalActivite.isPresent() && optionalActivite.get().getCoach().getId_user().equals(user.getId_user())) {
            Activite activite = optionalActivite.get();
            activite.setNomActivite(nomActivite);
            activite.setDateDebutActivite(LocalDateTime.parse(dateDebut));
            activite.setDateFinActivite(LocalDateTime.parse(dateFin));
            activite.setSalle(salle);
            activite.setMaxParticipants(maxParticipants);
            activite.setEquipements(equipementRepository.findAllById(equipements));
            activiteRepository.save(activite);
        }
        return "redirect:/activites";
    }

    //delete an activity
    @PostMapping("/delete/{id}")
    public String deleteActivite(@PathVariable Long id, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null || !user.getRole().getRole().equals("COACH")) {
            return "redirect:/login";
        }

        Optional<Activite> optionalActivite = activiteRepository.findById(id);
        if (optionalActivite.isPresent() && optionalActivite.get().getCoach().getId_user().equals(user.getId_user())) {
            activiteRepository.deleteById(id);
        }

        return "redirect:/activites";
    }




    /* klaaada hedha l code l9dim tw nchoufouh fl admin
    // Display all activities on the HTML page
    @GetMapping
    public String index(Model model) {
        List<Activite> activites = activiteRepository.findAll();
        model.addAttribute("activites", activites);
        return "activite";
    }

    // Add a new activity
    @PostMapping("/add")
    public String addActivite(@ModelAttribute Activite activite) {
        activiteRepository.save(activite);
        return "redirect:/activites";
    }


    // Display edit page
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Activite> activite = activiteRepository.findById(id);
        if (activite.isPresent()) {
            model.addAttribute("activite", activite.get());
            return "edit-activite";
        } else {
            return "redirect:/activites"; // Redirect to activity list if not found
        }
    }

    // Update activity
    @PostMapping("/update/{id}")
        public String updateActivite(@PathVariable Long id, @ModelAttribute Activite activiteDetails) {
        Optional<Activite> activiteOptional = activiteRepository.findById(id);
        if (activiteOptional.isPresent()) {
            Activite activiteToUpdate = activiteOptional.get();
            activiteToUpdate.setNomActivite(activiteDetails.getNomActivite());
            activiteToUpdate.setDateDebutActivite(activiteDetails.getDateDebutActivite());
            activiteToUpdate.setDateFinActivite(activiteDetails.getDateFinActivite());

            activiteRepository.save(activiteToUpdate);
            return "redirect:/activites";
        } else {
            return "redirect:/activites";
        }
    }


    // Delete activity
    @PostMapping("/delete/{id}")
    public String deleteActivite(@PathVariable long id) {
        if (activiteRepository.existsById(id)) {
            activiteRepository.deleteById(id);
        }
        return "redirect:/activites";
    }

     */

}
