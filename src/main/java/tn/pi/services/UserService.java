package tn.pi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.pi.entities.Role;
import tn.pi.entities.UserEntity;
import tn.pi.repositories.RoleRepository;
import tn.pi.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void registerUser(String firstName, String lastName, String username, String password, String roleName) {
        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists!");
        }

        Role role = roleRepository.findByRole(roleName);
        if (role == null) {
            throw new IllegalArgumentException("Role not found!");
        }

        UserEntity user = new UserEntity();
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        user.setUsername(username);
        user.setPassword(password);
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
}
