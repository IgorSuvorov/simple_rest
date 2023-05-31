package com.example.crudrestapi.service;

import com.example.crudrestapi.model.User;
import com.example.crudrestapi.payload.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Igor Suvorov
 */

public interface UserService {
    UserDTO createUser(UserDTO user);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(UserDTO userDTO);

    void deleteUser(Long id);
}
