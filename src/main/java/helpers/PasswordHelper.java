package helpers;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordHelper {

    public static String generatePasswordHash(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    public static String generateSalt() {
        return BCrypt.gensalt();
    }

    public static boolean validatePassword(String password, String passwordHash, String salt) {
        if (passwordHash.equals(generatePasswordHash(password, salt))) {
            return true;
        }
        return false;
    }
}
