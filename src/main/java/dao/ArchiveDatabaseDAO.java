package dao;

import models.ArchiveDatabase;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ArchiveDatabaseDAO implements DAOInterface<ArchiveDatabase> {
    @Autowired
    private SessionFactory sessionFactory;

    public ArchiveDatabaseDAO() {}

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(ArchiveDatabase obj) {
        sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    public void update(ArchiveDatabase obj) {
        sessionFactory.getCurrentSession().update(obj);
    }

    @Override
    public ArchiveDatabase getById(Integer id) {
        return (ArchiveDatabase) sessionFactory.getCurrentSession().get(ArchiveDatabase.class, id);
    }

    @Override
    public List<ArchiveDatabase> getAll() {
        return (List<ArchiveDatabase>) sessionFactory.
                getCurrentSession().
                createCriteria(ArchiveDatabase.class).
                list();
    }
}
