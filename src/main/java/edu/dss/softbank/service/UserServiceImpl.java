package edu.dss.softbank.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.dss.softbank.domain.Role;
import edu.dss.softbank.domain.User;
import edu.dss.softbank.repositories.RoleRepository;
import edu.dss.softbank.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(User u) {
        User user = new User();
        user.setEmail(u.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(u.getPasswordAsString()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public void updateUser(User u) {
        User user = userRepository.findByEmail(u.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(u.getPasswordAsString()));
/*
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));*/ // I read somewhere that Hibernate, for this instance here, 
                                              // don't like Array.asList because it's immutable, thus yielding errors
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private User mapToUserDto(User u){
        User user = new User();
        user.setId(u.getId());
        user.setEmail(u.getEmail());
        return user;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

}
