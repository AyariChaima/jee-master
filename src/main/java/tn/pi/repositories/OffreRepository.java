package tn.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entities.Offre;

public interface OffreRepository extends JpaRepository<Offre, Long> {
}
