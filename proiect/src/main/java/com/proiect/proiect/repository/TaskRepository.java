package com.proiect.proiect.repository;

import com.proiect.proiect.entities.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {

}