package tn.pi.repositories.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.pi.entities.UserEntity;
import java.util.List;

public interface AdminCoachRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.role.id = 3")
    List<UserEntity> findAllCoaches();

}