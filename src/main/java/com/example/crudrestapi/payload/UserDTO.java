package com.example.crudrestapi.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * @author Igor Suvorov
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    @NotEmpty(message = "User's first name must not be null or empty")
    private String firstName;
    @NotEmpty(message = "User's last name must not be null or empty")
    private String lastName;
    @NotEmpty(message = "User's email address must not be null or empty")
    @Email(message = "User's email address must be a valid email address")
    private String email;
}
