package tn.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entities.Adherent;

public interface AdherentRepository extends JpaRepository<Adherent, Long> {
}