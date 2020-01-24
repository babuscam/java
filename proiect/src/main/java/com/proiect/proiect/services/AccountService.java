package com.proiect.proiect.services;

import com.proiect.proiect.entities.User;
import com.proiect.proiect.models.RegisterModel;
import com.proiect.proiect.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class AccountService {
    private UserRepository userRepository;

    public AccountService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void Register(RegisterModel model){
        var user = userRepository.FindByEmail(model.getEmail());

        if(user != null){
            throw new EntityExistsException("Email already exists.");
        }

        BCryptPasswordEncoder ps = new BCryptPasswordEncoder();
        String password = ps.encode(model.getPassword());

        userRepository.save(new User(model.getEmail(), model.getFirstName(), model.getLastName(), password));
    }

    /*public void Login(LoginModel model){
        var user = userRepository.FindByEmail(model.getEmail());

        if(user == null){
            throw new EntityNotFoundException("User doesn't exist.");
        }

        BCryptPasswordEncoder ps = new BCryptPasswordEncoder();
        var hashPass = ps.encode(model.getPassword());

        if(user.getPassword() != hashPass){
            throw new EntityNotFoundException("Password is incorrect.");
        }
    }*/
}
