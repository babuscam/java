package com.proiect.proiect.services;

import com.proiect.proiect.entities.Team;
import com.proiect.proiect.models.TeamModel;
import com.proiect.proiect.repository.ProjectRepository;
import com.proiect.proiect.repository.TeamRepository;
import com.proiect.proiect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {
    private TeamRepository teamRepository;
    private UserRepository userRepository;
    private ProjectRepository projectRepository;

    public TeamService(TeamRepository teamRepository, UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.projectRepository = projectRepository;
    }

    public TeamModel CreateTeam(TeamModel model){
        var exists = teamRepository.findByName(model.name);

        if(exists != null){
            throw new EntityExistsException("Team already exists");
        }

        var leader = userRepository.findById(model.leaderId);

        var team = new Team(model.name, leader);
        teamRepository.save(team);

        model.id = team.getId();

        return model;
    }

    public List<TeamModel> GetTeams(){
        var teams = teamRepository.findAll();
        var entities = new ArrayList<TeamModel>();

        for (var team: teams) {
            entities.add(new TeamModel(team.getId(), team.getName(), team.getLeader().getId()));
        }

        return entities;
    }

    public void EditTeam(TeamModel model){
        var team = teamRepository.findById(model.id);

        if(team == null){
            throw new EntityNotFoundException("Team doesn't exist.");
        }

        if(model.name != team.get().getName()){
            var checkName = teamRepository.findByName(model.name);

            if(checkName != null){
                throw new EntityExistsException("This name already exists.");
            }

            team.get().setName(model.name);
        }

        if(team.get().getLeader().getId() != model.leaderId){
            var leader = userRepository.findById(model.leaderId);

            if(leader == null){
                throw new EntityNotFoundException("This leader doesn't exist.");
            }

            team.get().setLeader(leader.get());
        }

        teamRepository.save(team.get());
    }

    public void DeleteTeam(Integer id){
        var team = teamRepository.findById(id);

        if(team == null){
            throw new EntityNotFoundException("Team doesn't exist.");
        }

        userRepository.RemoveTeam(id);
        projectRepository.RemoveTeam(id);
        teamRepository.delete(team.get());
    }
}
