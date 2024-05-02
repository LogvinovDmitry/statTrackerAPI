package logvinov.testTask.userRestApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Null(message = "ID must be null during creation")
    private Long id;

    @NotEmpty(message = "This field cannot be empty")
    @Email(message = "You provided an invalid email")
    private String email;

    @NotEmpty(message = "This field cannot be empty")
    private String firstName;

    @NotEmpty(message = "This field cannot be empty")
    private String lastName;

    @NotNull(message = "This field cannot be empty")
    @Past(message = "This field cannot be in the future")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    private String address;

    private String phoneNumber;
}
