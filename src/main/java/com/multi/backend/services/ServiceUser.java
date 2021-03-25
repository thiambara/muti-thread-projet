package com.multi.backend.services;

import java.util.Arrays;
import java.util.List;

import com.multi.backend.models.User;
import com.multi.backend.repositories.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
@Transactional
public class ServiceUser implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private Conv<User> conv;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserById(Long id) {
        if (id == null) {
            throw new NullPointerException("id user not provied");
        }
        return this.userRepo.findById(id).orElseThrow(() -> new RuntimeException("user with id " + id + " not found"));
    }

    @Transactional(rollbackFor = Exception.class)
    public User addUser(User user) {
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        return this.userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return this.userRepo.findAll();
    }

    public User updateUser(User user) {
        User registeredUser = this.getUserById(user.getId());
        List<String> fields = Arrays.asList("firstName", "lastName", "email");
        registeredUser = this.conv.pour(registeredUser, user, fields);
        return this.userRepo.save(registeredUser);
    }

    public void deleteUser(Long id) {
        this.userRepo.deleteById(id);
    }

    public User getAuthenticateUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return this.userRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with username %s not found", username)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with username %s not found", username)));
    }

    public Boolean changeAuthenticatedUserPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new NullPointerException("Le mot de passe fourni n'est pas valide");
        }
        User user = this.getAuthenticateUser();
        String encodedPassword = this.passwordEncoder.encode(password.trim());
        user.setPassword(encodedPassword);
        this.userRepo.save(user);
        return true;
    }
}
