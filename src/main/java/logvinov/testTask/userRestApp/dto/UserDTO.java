package logvinov.testTask.userRestApp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Null(message = "ID must be null during creation")
    private String id;

    @NotEmpty(message = "This field cannot be empty")
    @Email(message = "You provided an invalid email")
    private String email;

    @NotEmpty(message = "This field cannot be empty")
    private String firstName;

    @NotEmpty(message = "This field cannot be empty")
    private String lastName;

    @NotNull(message = "This field cannot be empty")
    @Past(message = "This field cannot be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    private String address;

    private String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null")
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password confirmation must be not null")
    private String passwordConfirmation;
}
