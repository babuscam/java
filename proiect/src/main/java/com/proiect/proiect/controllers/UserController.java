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

    @RequestMapping(value = "/GetUsers", method= RequestMethod.GET)
    public ResponseEntity<List<UserModel>> GetUsers(){
        return ResponseEntity.ok(userService.GetUsers());
    }

    @RequestMapping(value = "/EditUser", produces = "application/json", method=RequestMethod.PUT)
    public void EditUser(UserModel model){
        userService.EditUser(model);
    }

    @RequestMapping(value = "/DeleteUser/{id}", method=RequestMethod.DELETE)
    public void DeleteUser(@PathVariable Integer id){
        userService.DeleteUser(id);
    }
}
