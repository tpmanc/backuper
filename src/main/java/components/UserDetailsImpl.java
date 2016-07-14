package components;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class UserDetailsImpl implements UserDetails {
    private int id;
    private String username;
    private String password;
    private Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public UserDetailsImpl(String username, String password,
                           Collection<GrantedAuthority> authorities, int id) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.id = id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getId() {
        return id;
    }
}
