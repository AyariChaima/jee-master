package tn.pi.repositories.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entities.Activite;

public interface AdminActivityRepository extends JpaRepository<Activite, Long> {
}