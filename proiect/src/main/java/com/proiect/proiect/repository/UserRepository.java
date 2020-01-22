package com.proiect.proiect.repository;

import com.proiect.proiect.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}