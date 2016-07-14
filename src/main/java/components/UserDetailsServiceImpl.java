package components;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import services.UserService;

import java.util.Collection;
import java.util.HashSet;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Username or password incorrect!");
        }
        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        return new CustomUser(user.getEmail(), user.getPasswordHash(), authorities, user.getId());
    }
}
