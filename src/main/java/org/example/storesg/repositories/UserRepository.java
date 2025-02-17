package org.example.storesg.repositories;

import org.example.storesg.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {
    <List>User findById(Integer id);
}
