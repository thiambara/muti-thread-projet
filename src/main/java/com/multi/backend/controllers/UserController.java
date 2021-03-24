package com.multi.backend.controllers;

import java.util.List;

import com.multi.backend.models.User;
import com.multi.backend.services.ServiceUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private ServiceUser serviceUser;

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> user = this.serviceUser.getAllUsers();
        return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {

        User user = this.serviceUser.getUserById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        user = this.serviceUser.addUser(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        user.setId(id);
        User updatedUser = this.serviceUser.updateUser(user);
        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        User user = this.serviceUser.getUserById(id);
        this.serviceUser.deleteUser(user.getId());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/authenticated-user")
    public ResponseEntity<User> rien() {
        User user = this.serviceUser.getAuthenticateUser();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}
