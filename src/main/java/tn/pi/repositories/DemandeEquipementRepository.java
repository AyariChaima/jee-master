package tn.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entities.DemandeEquipement;
import tn.pi.entities.UserEntity;

import java.util.List;

public interface DemandeEquipementRepository extends JpaRepository<DemandeEquipement, Long> {
    // Custom query to find demands by coach
    List<DemandeEquipement> findByCoach(UserEntity coach);
}
