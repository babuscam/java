package com.proiect.proiect.services;

import com.proiect.proiect.models.ProjectModel;
import com.proiect.proiect.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectModel EditProject(ProjectModel model){
        var project = projectRepository.findById(model.getId());

        if(project == null){
            throw new EntityNotFoundException("Project doesn't exist.");
        }

        var projectEntity = project.get();

        if(projectEntity.getId() != model.getId()){
            var projectId = projectRepository.findById(model.getId());

            if(projectId != null){
                throw new EntityExistsException("Project already exists.");
            }
        }

        projectEntity.setName(model.getName());
        projectEntity.setDescription(model.getDescription());

        projectRepository.save(projectEntity);

        return model;
    }

    public List<ProjectModel> GetProjects(){
        var projects = projectRepository.findAll();
        var entities = new ArrayList<ProjectModel>();

        for (var project: projects) {
            entities.add(new ProjectModel(project.getId(), project.getName(), project.getDescription(), project.isActive(), project.getTeam()));
        }

        return entities;
    }

    public void DeleteProject(Integer id){
        var project = projectRepository.findById(id);

        if(project == null){
            throw new EntityNotFoundException("Project doesn't exist.");
        }

        var projectEntity = project.get();

        projectRepository.RemoveTask(id);
        projectRepository.delete(projectEntity);
    }
}
