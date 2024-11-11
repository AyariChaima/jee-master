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
public class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActivite;
    private String nomActivite;
    private Date dateDebutActivite;
    private Date dateFinActivite;
    /*private Coach coach;*/
}
