package tn.pi.repositories.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entities.Paiement;

public interface AdminPaymentRepository extends JpaRepository<Paiement, Long> {
}
