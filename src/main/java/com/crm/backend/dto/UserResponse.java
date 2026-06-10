package com.crm.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "User response returned by the API")
public class UserResponse {

    @Schema(description = "User id")
    private UUID id;

    @Schema(description = "User email")
    private String email;

    @Schema(description = "Role")
    private String role;

    @Schema(description = "Created timestamp")
    private Instant createdAt;

    @Schema(description = "Updated timestamp")
    private Instant updatedAt;
}
