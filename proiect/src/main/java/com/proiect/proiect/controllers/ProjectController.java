package com.proiect.proiect.controllers;


import com.proiect.proiect.models.ProjectModel;
import com.proiect.proiect.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/project")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = "/CreateProject", produces = "application/json", method= RequestMethod.POST)
    public ResponseEntity<ProjectModel> CreateProject(@RequestBody ProjectModel model){
        return ResponseEntity.ok(projectService.EditProject(model));
    }

    @RequestMapping(value = "/GetProject", method= RequestMethod.GET)
    public ResponseEntity<List<ProjectModel>> GetProject(){
        return ResponseEntity.ok(projectService.GetProjects());
    }

    @RequestMapping(value = "/EditProject", method= RequestMethod.PUT)
    public void EditProject(@RequestBody ProjectModel model){
        projectService.EditProject(model);
    }

    @RequestMapping(value = "/DeleteProject/{id}", method= RequestMethod.DELETE)
    public void DeleteProject(@PathVariable Integer id){
        projectService.DeleteProject(id);
    }
}
