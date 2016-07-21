package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BashExecuteException extends Exception {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public BashExecuteException(String message) {
        this.message = message;
    }
}
