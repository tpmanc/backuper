package validators;

import com.jcraft.jsch.JSchException;
import helpers.SshConnect;
import models.BackupDatabase;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BackupDatabaseValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return BackupDatabase.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BackupDatabase backupDatabase = (BackupDatabase) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.empty", "Title is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "databaseName", "databaseName.empty", "Database name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "databaseUser", "databaseUser.empty", "Database user is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "databasePassword", "databasePassword.empty", "Database password is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "databasePort", "databasePort.empty", "Database port is required");

        if (backupDatabase.getDatabaseType() <= 0) {
            errors.rejectValue("databaseType", "databaseType.empty", "Database type is required");
        }

        if (backupDatabase.getServer() == null) {
            errors.rejectValue("server", "server.empty", "Server is required");
        }
    }
}
