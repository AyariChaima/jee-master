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
public class DemandeEquipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDemande;

    private String nomEquipement; // Name of the equipment being requested

    private String descriptionDemande; // Details about the requested equipment

    private Date dateDemande; // Date of the request

    @ManyToOne
    private UserEntity coach; // The coach making the request

    private String status; // Status of the demand (e.g., PENDING, APPROVED, REJECTED)
}