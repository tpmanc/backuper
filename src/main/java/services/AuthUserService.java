package services;

import authentication.CustomUser;
import dao.AuthUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService implements UserDetailsService {
    @Autowired
    private AuthUserDAO dao;

    public void setDao(AuthUserDAO dao) {
        this.dao = dao;
    }

    @Override
    public CustomUser loadUserByUsername(String email) throws UsernameNotFoundException {
        return dao.getByEmail(email);
    }
}
