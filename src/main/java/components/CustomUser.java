package components;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class CustomUser extends User {
    private int id;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, int id) {
        super(username, password, authorities);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
