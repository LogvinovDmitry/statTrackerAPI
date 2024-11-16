package logvinov.testTask.userRestApp.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorBuilder {
    public static String buildErrors(BindingResult bindingResult) {
        StringBuilder errorsBuilder = new StringBuilder();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            errorsBuilder.append(fieldError.getField())
                    .append(" - ").append(fieldError.getDefaultMessage()).append(";");
        }

        return errorsBuilder.toString();

    }
}