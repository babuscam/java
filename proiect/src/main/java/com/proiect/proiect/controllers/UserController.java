package com.proiect.proiect.controllers;

import com.proiect.proiect.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
