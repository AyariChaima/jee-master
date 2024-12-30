package tn.pi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
