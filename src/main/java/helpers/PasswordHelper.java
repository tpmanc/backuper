package helpers;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordHelper {
    private static String salt = "$2a$10$0R5oFk5KMPd8gJS7UdFbze";

    public static String generatePasswordHash(String password) {
        return BCrypt.hashpw(password, salt);
    }

    public static boolean validatePassword(String password, String passwordHash) {
        if (passwordHash.equals(generatePasswordHash(password))) {
            return true;
        }
        return false;
    }
}
