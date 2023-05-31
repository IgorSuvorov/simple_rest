package com.example.crudrestapi.repository;

import com.example.crudrestapi.model.User;
import com.example.crudrestapi.payload.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Igor Suvorov
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
