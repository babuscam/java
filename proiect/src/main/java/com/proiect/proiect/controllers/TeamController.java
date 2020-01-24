package com.proiect.proiect.controllers;

import com.proiect.proiect.models.TeamModel;
import com.proiect.proiect.services.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/team")
public class TeamController {
    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping()
    public ResponseEntity<TeamModel> CreateTeam(@RequestBody TeamModel model){
        return ResponseEntity.ok(teamService.CreateTeam(model));
    }

    @GetMapping()
    public ResponseEntity<List<TeamModel>> GetTeams(){
        return ResponseEntity.ok(teamService.GetTeams());
    }

    @PutMapping
    public void EditTeam(@RequestBody TeamModel model){
        teamService.EditTeam(model);
    }

    @DeleteMapping("/{id}")
    public void DeleteTeam(@PathVariable Integer id){
        teamService.DeleteTeam(id);
    }
}
