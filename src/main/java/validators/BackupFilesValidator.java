package validators;

import models.BackupFiles;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BackupFilesValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return BackupFiles.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BackupFiles backupFiles = (BackupFiles) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.empty", "Title is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "folder", "folder.empty", "Folder name is required");

        if (backupFiles.getServer() == null) {
            errors.rejectValue("server", "server.empty", "Server is required");
        }
    }
}
