package com.proiect.proiect.services;

import com.proiect.proiect.models.TaskModel;
import com.proiect.proiect.repository.TaskRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;


    public TaskService(TaskRepository taskService) {
        this.taskRepository = taskService;
    }

    public List<TaskModel> GetTasks(){
        var tasks = taskRepository.findAll();

        var entities = new ArrayList<TaskModel>();

        for (var task: tasks) {
            entities.add(new TaskModel(task.getId() ,task.getName(), task.getDescription(), task.getStatus(), task.getProject(), task.getUser()));
        }

        return entities;
    }

    public TaskModel EditTask(TaskModel model){
        var task = taskRepository.findById(model.getId());

        if(task == null){
            throw new EntityNotFoundException("Task doesn't exist.");
        }

        var taskEntity = task.get();

        if(taskEntity.getId() != model.getId()){
            var taskId = taskRepository.findById(model.getId());

            if(taskId != null){
                throw new EntityExistsException("Task already exists.");
            }

        }

        taskEntity.setName(model.getName());
        taskEntity.setDescription(model.getDescription());

        taskEntity.setUser(model.getUser());
        taskEntity.setStatus(model.getStatus());
        taskEntity.setProject(model.getProject());

        taskRepository.save(taskEntity);

        return model;
    }

    public void DeleteTask(Integer id){
        var task = taskRepository.findById(id);

        if(task == null){
            throw new EntityNotFoundException("Task doesn't exist.");
        }

        var taskEntity = task.get();

        taskRepository.delete(taskEntity);
    }
}
