package logvinov.testTask.userRestApp.dto.auth;

import lombok.Data;

@Data
public class JwtResponse {

    private String id;
    private String email;
    private String accessToken;
    private String refreshToken;
}