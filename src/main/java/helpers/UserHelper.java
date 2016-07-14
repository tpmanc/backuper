package helpers;

import authentication.CustomUser;
import org.springframework.security.core.Authentication;

import java.security.Principal;

public class UserHelper {
    public static CustomUser getCustomUser(Principal principal) {
        return (CustomUser) ((Authentication) principal).getPrincipal();
    }
}
