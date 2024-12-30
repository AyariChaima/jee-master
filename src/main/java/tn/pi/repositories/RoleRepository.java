package tn.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String name);
}
