package com.proiect.proiect.repository;

import com.proiect.proiect.entities.Project;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
    @Transactional
    @Modifying
    @Query("update Project set team_id = null where team_id = ?1")
    void RemoveTeam(Integer teamId);
    @Transactional
    @Modifying
    @Query("update Project set team_id = null where team_id = ?1")
    void RemoveTask(Integer taskId);
}