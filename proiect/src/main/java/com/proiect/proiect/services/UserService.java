package com.proiect.proiect.services;

import com.proiect.proiect.entities.Task;
import com.proiect.proiect.entities.User;
import com.proiect.proiect.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepo;

    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers(){
        Iterable<User> usersDB = userRepo.findAll();
        List<User> users = new ArrayList<User>();

        for (var user: usersDB) {
            var entity = new User();
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setEmail(user.getEmail());

            var tasks = user.getTasks();
            Hibernate.initialize(tasks);
            var task = new Task();
            task.setName(tasks.get(0).getName());
            var status = tasks.get(0).getStatus();
            task.setStatus(status);
            task.setDescription(tasks.get(0).getDescription());

            var ts = new ArrayList<Task>();
            ts.add(task);

            entity.setTasks(ts);

            //entity.setTasks();
            users.add(entity);
        }

        return users;
    }
}
