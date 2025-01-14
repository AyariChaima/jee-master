package tn.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entities.Activite;
import tn.pi.entities.Participation;
import tn.pi.entities.UserEntity;

import java.util.Optional;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    Optional<Participation> findByUserAndActivite(UserEntity user, Activite activite);
    int countByActivite(Activite activite); // Count total participants for a specific activity

}
