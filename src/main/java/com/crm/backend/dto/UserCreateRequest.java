package com.crm.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request to create a user")
public class UserCreateRequest {

    @Email
    @NotBlank
    @Schema(description = "User email", example = "user@example.com", required = true)
    private String email;

    @NotBlank
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).+$",
            message = "Password must contain uppercase, lowercase, digit and special character")
    @Schema(description = "Plain text password", required = true)
    private String password;

    @Schema(description = "Role of the user", example = "SALES")
    private String role;
}
