package tn.pi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaiement;
    private Date datePaiement;
    private String typePaiement;
    private Double montantPaiement;
}
