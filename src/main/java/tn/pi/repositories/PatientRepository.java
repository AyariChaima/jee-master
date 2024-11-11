package tn.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
