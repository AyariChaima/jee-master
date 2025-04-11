package tn.pi.repositories.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entities.Equipement;

public interface AdminEquipementRepository extends JpaRepository<Equipement, Long> {
}
