package com.multi.backend.services;

import java.util.Arrays;
import java.util.List;

import com.multi.backend.models.User;
import com.multi.backend.repositories.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
@Transactional
public class ServiceUser {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private Conv<User> conv;



    public User getUserById(Long id) {
        if(id == null){
            throw new NullPointerException("id user not provied");
        }
        return this.userRepo.findById(id).orElseThrow(() -> new RuntimeException("user with id " + id + " not found"));
    }

    public User addUser(User user) {
        return this.userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return this.userRepo.findAll();
    }

    public User updateUser(User user) {
        User registeredUser = this.getUserById(user.getId());
        List<String> fields = Arrays.asList("username");
        registeredUser = this.conv.pour(registeredUser, user,fields);
        return this.userRepo.save(registeredUser);
    }

    public void deleteUser(Long id) {
        this.userRepo.deleteById(id);
    }
}
