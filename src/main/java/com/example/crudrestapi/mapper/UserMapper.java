package com.example.crudrestapi.mapper;

import com.example.crudrestapi.model.User;
import com.example.crudrestapi.payload.UserDTO;

/**
 * @author Igor Suvorov
 */
public class UserMapper {

    public static UserDTO convertEntityToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    public static User convertDTOToEntity(UserDTO userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail()
        );
    }
}
