package tn.pi.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.Equipement;
import tn.pi.entities.Role;
import tn.pi.entities.UserEntity;
import tn.pi.repositories.admin.AdminUserRepository;
import tn.pi.repositories.RoleRepository;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String listUsers(Model model) {
        List<UserEntity> users = adminUserRepository.findAllUsers();
        model.addAttribute("users", users);
        return "admin/users";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "admin/add-user";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute UserEntity user) {
        Role role = roleRepository.findByRole("USER");
        user.setRole(role);
        adminUserRepository.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        UserEntity user = adminUserRepository.findById(id).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
            return "admin/edit-user";
        }
        return "redirect:/admin/users";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute UserEntity userDetails) {
        UserEntity user = adminUserRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setFirst_name(userDetails.getFirst_name());
            user.setLast_name(userDetails.getLast_name());
            user.setEmail(userDetails.getEmail());
            user.setPhone_number(userDetails.getPhone_number());
            adminUserRepository.save(user);
        }
        return "redirect:/admin/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        adminUserRepository.deleteById(id);
        return "redirect:/admin/users";
    }
}