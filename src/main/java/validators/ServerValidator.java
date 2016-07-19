package validators;

import com.jcraft.jsch.JSchException;
import helpers.SshConnect;
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
        Server server = (Server) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.empty", "Title is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "host", "host.empty", "Host is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sshUser", "sshUser.empty", "Ssh user is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sshPassword", "sshPassword.empty", "Ssh password is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sshPort", "sshPort.empty", "Ssh port is required");

        if (server.getUser() == null) {
            errors.rejectValue("user", "user.empty", "User is required");
        }

        if (errors.getFieldErrors().size() == 0) {
            try {
                SshConnect connection = new SshConnect(server.getHost(), server.getSshPort(), server.getSshUser(), server.getSshPassword());
                connection.connect();
                connection.disconnect();
            } catch (JSchException e) {
                errors.rejectValue("host", "host.error", e.getMessage());
                errors.rejectValue("sshUser", "sshUser.error", e.getMessage());
                errors.rejectValue("sshPassword", "sshPassword.error", e.getMessage());
                errors.rejectValue("sshPort", "sshPort.error", e.getMessage());
            }
        }
    }
}
