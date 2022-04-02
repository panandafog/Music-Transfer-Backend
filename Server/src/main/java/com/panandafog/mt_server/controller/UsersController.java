package com.panandafog.mt_server.controller;

import com.panandafog.mt_server.entity.User;
import com.panandafog.mt_server.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService service;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("User password:");
        System.out.println(user.getPassword());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        System.out.println("Encoded password:");
        System.out.println(encodedPassword);
        user.setPassword(encodedPassword);

        service.save(user);

        return "register_success";
    }
}
