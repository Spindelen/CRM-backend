package com.crm.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Authentication response with JWT tokens")
public class AuthResponse {

    @Schema(description = "Access token (short-lived)", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String accessToken;

    @Schema(description = "Refresh token (long-lived)", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String refreshToken;

    @Schema(description = "Token type", example = "Bearer")
    private String tokenType;

    @Schema(description = "Access token expiry in milliseconds", example = "900000")
    private long expiresIn;

    @Schema(description = "User email", example = "user@example.com")
    private String email;

    @Schema(description = "User role", example = "SALES")
    private String role;
}
