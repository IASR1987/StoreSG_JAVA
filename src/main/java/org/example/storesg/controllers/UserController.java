package org.example.storesg.controllers;

import org.example.storesg.entities.User;
import org.example.storesg.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping(path = "/userById")
    public User getUserById(@RequestParam Integer id) {
        return userRepository.findById(id).orElse(null);
    }

}
