package tn.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.pi.entities.Activite;

import java.util.List;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {
    @Query("SELECT a FROM Activite a WHERE a.coach.id_user = :coachId")
    List<Activite> findByCoachId(@Param("coachId") Long coachId);
}
