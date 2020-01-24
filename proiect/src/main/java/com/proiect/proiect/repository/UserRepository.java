package com.proiect.proiect.repository;

import com.proiect.proiect.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query("update User set team_id = null where team_id = ?1")
    void RemoveTeam(Integer teamId);

    @Query("select t from User t where t.email=?1")
    User FindByEmail(String email);

    @Transactional
    @Modifying
    @Query("delete from role where username = ?1")
    void RemoveRole(String email);
}
