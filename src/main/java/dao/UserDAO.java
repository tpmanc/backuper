package dao;

import models.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO implements DAOInterface<User> {
    @Autowired
    private SessionFactory sessionFactory;

    public UserDAO() {
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(User obj) {
        sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    public void update(User obj) {
        sessionFactory.getCurrentSession().update(obj);
    }

    @Override
    public User getById(Integer id) {
        return (User) sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }

    public User getByEmail(String email) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        return (User) criteria.add(Restrictions.eq("email", email)).uniqueResult();
    }
}
