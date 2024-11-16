package logvinov.testTask.userRestApp.dto.auth;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class JwtRefreshTokenRequest {
    @NotNull(message = "This field cannot be empty")
    private String refreshToken;
}