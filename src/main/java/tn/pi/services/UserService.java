package tn.pi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.pi.entities.Role;
import tn.pi.entities.UserEntity;
import tn.pi.repositories.RoleRepository;
import tn.pi.repositories.UserRepository;
import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void registerUser(String firstName, String lastName, String username, String password, Integer age,
                             String email, String phoneNumber, String emergencyPhoneNumber, String dateOfInscription, String RoleName) {
        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists!");
        }
        Role role = roleRepository.findByRole(RoleName);

        UserEntity user = new UserEntity();
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setAge(age);
        user.setEmail(email);
        user.setPhone_number(phoneNumber);
        user.setEmergency_phone_number(emergencyPhoneNumber);
        user.setDate_of_inscription(LocalDate.parse(dateOfInscription));
        user.setRole(role);
        userRepository.save(user);
    }

    public UserEntity loginUser(String username, String password) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid username or password!");
        }
        return user;
    }

    public void updateUser(UserEntity user) {
        userRepository.save(user);
    }

}
