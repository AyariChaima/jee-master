package tn.pi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder

public class Adherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_adherent;
    private String nom_adherent;
    private String prenom_adherent;
    private int age;
    private String email;
    private LocalDate date_inscription;
}
