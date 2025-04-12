package tn.pi.entities;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

    private LocalDateTime dateDebutActivite;

    private LocalDateTime  dateFinActivite;

    private String salle; // Name of the room where the activity will take place

    private int maxParticipants; // Maximum number of participants allowed

    @ManyToMany
    @JoinTable(
            name = "activite_equipement",
            joinColumns = @JoinColumn(name = "activite_id"),
            inverseJoinColumns = @JoinColumn(name = "equipement_id")
    )
    private List<Equipement> equipements; // List of equipment needed for the activity

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private UserEntity coach;
}
