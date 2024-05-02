package logvinov.testTask.userRestApp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserErrorResponse {
    private String message;

    private String timestamp;

    public UserErrorResponse(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp.toString();
    }
}
