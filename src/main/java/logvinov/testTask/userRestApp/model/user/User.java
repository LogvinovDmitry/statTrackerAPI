package logvinov.testTask.userRestApp.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.Set;

@Document(collection = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    private String email;

    private String firstName;

    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    private String address;

    private String phoneNumber;

    private String password;

    @Transient
    private String passwordConfirmation;

    private Set<Role> roles;
}