package com.proiect.proiect.services;

import com.proiect.proiect.entities.Task;
import com.proiect.proiect.entities.User;
import com.proiect.proiect.models.UserModel;
import com.proiect.proiect.repository.TaskRepository;
import com.proiect.proiect.repository.TeamRepository;
import com.proiect.proiect.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepo;
    private TeamRepository teamRepository;
    private TaskRepository taskRepository;

    public UserService(UserRepository userRepo, TeamRepository teamRepository, TaskRepository taskRepository){
        this.taskRepository = taskRepository;
        this.userRepo = userRepo;
        this.teamRepository = teamRepository;
    }

    public List<UserModel> GetUsers(){
        var users = userRepo.findAll();

        var entities = new ArrayList<UserModel>();

        for (var user: users) {
            entities.add(new UserModel(user.getId() ,user.getFirstName(), user.getLastName(), user.getEmail(), user.getTeam().getId()));
        }

        return entities;
    }

    public void EditUser(UserModel model){
        var user = userRepo.findById(model.getId());

        if(user == null){
            throw new EntityNotFoundException("User doesn't exist.");
        }

        var userEntity = user.get();

        if(userEntity.getEmail() != model.getEmail()){
            var email = userRepo.FindByEmail(model.getEmail());

            if(email != null){
                throw new EntityExistsException("Email already exists.");
            }

            userEntity.setEmail(model.getEmail());
        }

        userEntity.setFirstName(model.getFirstName());
        userEntity.setLastName(model.getLastName());

        if(userEntity.getTeam().getId() != model.getTeamId()){
            var team = teamRepository.findById(model.getId());

            userEntity.setTeam(team.get());
        }

        userRepo.save(userEntity);
    }

    public void DeleteUser(Integer id){
        var user = userRepo.findById(id);

        if(user == null){
            throw new EntityNotFoundException("User doesn't exist.");
        }

        var userEntity = user.get();

        userRepo.RemoveRole(userEntity.getEmail());
        taskRepository.RemoveUser(id);
        userRepo.delete(userEntity);
    }
}
