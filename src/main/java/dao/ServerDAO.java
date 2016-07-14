package dao;

import models.Server;
import models.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ServerDAO implements DAOInterface<Server> {

    @Autowired
    private SessionFactory sessionFactory;

    public ServerDAO() {
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Server server) {
        sessionFactory.getCurrentSession().save(server);
    }

    @Override
    public void update(Server server) {
        sessionFactory.getCurrentSession().update(server);
    }

    @Override
    public Server getById(Integer id) {
        return (Server) sessionFactory.getCurrentSession().get(Server.class, id);
    }

    @Override
    public List<Server> getAll() {
        return (List<Server>) sessionFactory.getCurrentSession().createCriteria(Server.class).list();
    }

    public List<Server> getAllByUserId(User user) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Server.class);
        return (List<Server>) criteria.add(Restrictions.eq("user", user)).list();
    }

}
