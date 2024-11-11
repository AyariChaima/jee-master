package tn.pi.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_coach;
    private String nom_coach;
    private String prenom_coach;
    private String email;
    private String specialite;
}
