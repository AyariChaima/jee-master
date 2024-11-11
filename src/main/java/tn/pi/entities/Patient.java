package tn.pi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor @Builder

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private LocalDate dateNaiss;
    private boolean malade;
    private int score;
}