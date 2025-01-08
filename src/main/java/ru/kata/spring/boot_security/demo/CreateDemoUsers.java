package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class CreateDemoUsers {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    @Autowired
    public CreateDemoUsers(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    public void createDemoUsers() {
        Set<Role> roleAdmin = new HashSet<>();
        Set<Role> roleUser = new HashSet<>();
        roleAdmin.add(new Role("ROLE_ADMIN"));
        roleUser.add(new Role("ROLE_USER"));
        User admin = new User("admin", "admin@mail.com",
                "$2y$10$yiInRFChgGpPRJlpWh21pONMAhrC39BiiDWwDEWOWuujsB5uBu/8W");
        User user = new User("user", "user@mail.com",
                "$2y$10$246J.d9ntA8E60YVAVN3bec2XggxkFLuVI.v3yarsbsLo2QBppJcC");

        admin.setRoles(roleAdmin);
        user.setRoles(roleUser);

        userRepository.save(user);
        userRepository.save(admin);
    }
}