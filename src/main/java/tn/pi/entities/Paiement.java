package tn.pi.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datePaiement;
    private Double montantPaiement;
    private String typePaiement;
    private Integer duration;
    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private Offre offre;

}
