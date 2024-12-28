package tn.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.pi.entities.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}
