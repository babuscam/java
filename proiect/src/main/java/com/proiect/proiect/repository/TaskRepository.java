package com.proiect.proiect.repository;

import com.proiect.proiect.entities.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


public interface TaskRepository extends CrudRepository<Task, Integer> {
    @Transactional
    @Modifying
    @Query("update Task set user_id = null where user_id = ?1")
    void RemoveUser(Integer userId);
}