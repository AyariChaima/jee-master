package tn.pi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tn.pi.entities.Patient;
import tn.pi.repositories.PatientRepository;

import java.util.List;

@Controller

public class PatientConstroller {

        //@Autowired
    private final PatientRepository patientRepository;

    public PatientConstroller(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patient")
    public String index(Model model) {
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        return "patients";
    }
}
