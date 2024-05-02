package logvinov.testTask.userRestApp.util;

import logvinov.testTask.userRestApp.dto.UserDTO;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Calendar;
import java.util.Date;

@Component
public class UserPatchDTOValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        UserDTO userPatchDTO = (UserDTO) o;

        if (userPatchDTO.getId() != null) {
            errors.rejectValue("id", "NotNull.userPatchDTO.id", "ID must be null during creation");
        }

        if (userPatchDTO.getBirthDate() != null && !userPatchDTO.getBirthDate().before(DateUtils.truncate(new Date(), Calendar.DATE))) {
            errors.rejectValue("birthDate", "Past.userPatchDTO.birthDate",
                    "This field cannot be in the future");

        }

        EmailValidator emailValidator = EmailValidator.getInstance();
        if (userPatchDTO.getEmail() != null && !emailValidator.isValid(userPatchDTO.getEmail())) {
            errors.rejectValue("email", "InvalidEmail", new Object[]{userPatchDTO.getEmail()},
                    "You provided an invalid email");
        }

    }
}
