package dao;

import models.Server;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    public void create(Server server) {
        sessionFactory.getCurrentSession().save(server);
    }

    public void update(Server server) {
        sessionFactory.getCurrentSession().update(server);
    }

    public Server getSite(Integer id) {
        return (Server) sessionFactory.getCurrentSession().get(Server.class, id);
    }

    @Override
    public List<Server> getAll() {
        return (List<Server>) sessionFactory.getCurrentSession().createCriteria(Server.class).list();
    }
}
