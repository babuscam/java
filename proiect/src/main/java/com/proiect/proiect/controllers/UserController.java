package com.proiect.proiect.controllers;

import com.proiect.proiect.models.UserModel;
import com.proiect.proiect.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> GetUsers(){
        return ResponseEntity.ok(userService.GetUsers());
    }

    @PutMapping
    public void EditUser(UserModel model){
        userService.EditUser(model);
    }

    @DeleteMapping
    public void DeleteUser(Integer id){
        userService.DeleteUser(id);
    }
}
