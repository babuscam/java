package com.proiect.proiect.repository;

import com.proiect.proiect.entities.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Integer> {
    @Query("select t from Team t where t.name = ?1")
    Team findByName(String name);
}