package tn.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entities.Equipement;

public interface EquipementRepository extends JpaRepository<Equipement, Long> {
}
