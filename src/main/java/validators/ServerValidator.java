package validators;

import models.Server;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ServerValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Server.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.empty", "Title is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.empty2", "Title is required2");
    }
}
