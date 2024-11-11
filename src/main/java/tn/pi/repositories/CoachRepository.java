package tn.pi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entities.Coach;
public interface CoachRepository extends JpaRepository<Coach, Long> {
}
