package tn.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entities.Activite;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {
}
