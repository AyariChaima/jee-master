package tn.pi.entities;

import jakarta.persistence.*;
import lombok.Data;
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

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
