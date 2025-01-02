package tn.pi.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    private String first_name;
    private String last_name;
    private String username;
    private String password;

    private Integer age;
    private String email;
    private String phone_number;
    private String emergency_phone_number;
    private LocalDate date_of_inscription;
    private String speciality;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Paiement> paiements; // List of payments made by this user
}
