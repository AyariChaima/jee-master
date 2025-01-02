package tn.pi.entities;

import jakarta.persistence.*;
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
    private Long idPaiement;
    private Date datePaiement;
    private Double montantPaiement;
    private String typePaiement; //carte visa , master , cash
    private Integer duration;
    @ManyToOne
    private UserEntity user; // Link to the user who made the payment

    @ManyToOne
    private Offre offre; // Link to the offer chosen by the user

}
