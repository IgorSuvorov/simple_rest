package com.example.crudrestapi.controller;

import com.example.crudrestapi.model.User;
import com.example.crudrestapi.payload.UserDTO;
import com.example.crudrestapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Igor Suvorov
 */
@Tag(
        name = "CRUD REST API for User Resource",
        description = "Create user, get user(s), update user, delete user"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users/")
public class UserController {

    private final UserService userService;

    // http://localhost:8080/api/users

    @Operation(
            summary = "Create user REST API",
            description = "Create User REST API is used to save user to the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.createUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get user by ID REST API",
            description = "Get user by ID REST API is used to retrieve a user from the database by the user's ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        UserDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(
            summary = "Get all user REST API",
            description = "Get all users REST API is used to retrieve all users from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(
            summary = "Update user REST API",
            description = "Update a user REST API is used to update a user's data in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("{id}/update")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id,
                                              @Valid @RequestBody UserDTO user) {
        user.setId(id);
        UserDTO updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete user REST API",
            description = "Delete a user REST API is used to delete a user's data in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity("User was deleted", HttpStatus.OK);
    }
}
