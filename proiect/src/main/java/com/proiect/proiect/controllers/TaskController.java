package com.proiect.proiect.controllers;

import com.proiect.proiect.models.TaskModel;
import com.proiect.proiect.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/task")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/CrateTask", produces = "application/json", method= RequestMethod.POST)
    public ResponseEntity<TaskModel> CrateTask(@RequestBody TaskModel model){
        return ResponseEntity.ok(taskService.EditTask(model));
    }

    @RequestMapping(value = "/GetTasks", method= RequestMethod.GET)
    public ResponseEntity<List<TaskModel>> GetTasks(){
        return ResponseEntity.ok(taskService.GetTasks());
    }

    @RequestMapping(value = "/EditProject", produces = "application/json", method= RequestMethod.PUT)
    public void EditProject(@RequestBody TaskModel model){
        taskService.EditTask(model);
    }

    @RequestMapping(value = "/DeleteProject/{id}", method= RequestMethod.DELETE)
    public void DeleteProject(@PathVariable Integer id){
        taskService.DeleteTask(id);
    }
}


