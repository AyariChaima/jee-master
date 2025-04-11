package tn.pi.repositories.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entities.Offre;

public interface AdminOfferRepository extends JpaRepository<Offre, Long> {
}
