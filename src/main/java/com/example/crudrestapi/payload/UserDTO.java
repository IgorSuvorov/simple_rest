package com.example.crudrestapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * @author Igor Suvorov
 */
@Schema(
        description = "User Data Transfer Object (DTO) model's information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @Schema(
            description = "User's first name"
    )
    @NotEmpty(message = "User's first name must not be null or empty")
    private String firstName;

    @Schema(
            description = "User's last name"
    )
    @NotEmpty(message = "User's last name must not be null or empty")
    private String lastName;

    @Schema(
            description = "User's email address"
    )
    @NotEmpty(message = "User's email address must not be null or empty")
    @Email(message = "User's email address must be a valid email address")
    private String email;
}
