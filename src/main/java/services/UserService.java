package services;

import dao.UserDAO;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO dao;

    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    @Transactional
    public void create(User obj) {
        dao.create(obj);
    }

    @Transactional
    public void update(User obj) {
        dao.update(obj);
    }

    @Transactional
    public User getById(Integer id) {
        return dao.getById(id);
    }

    @Transactional
    public List<User> getAll() {
        return dao.getAll();
    }

    @Transactional
    public User getByEmail(String email) {
        return dao.getByEmail(email);
    }
}
