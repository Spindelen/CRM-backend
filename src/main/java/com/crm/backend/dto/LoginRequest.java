package com.crm.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Login request credentials")
public class LoginRequest {

    @Email
    @NotBlank
    @Schema(description = "User email", example = "user@example.com", required = true)
    private String email;

    @NotBlank
    @Schema(description = "User password", example = "Password123!", required = true)
    private String password;
}
