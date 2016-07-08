package dao;

import models.BackupDatabase;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BackupDatabaseDAO implements DAOInterface<BackupDatabase> {
    @Autowired
    private SessionFactory sessionFactory;

    public BackupDatabaseDAO() {
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(BackupDatabase obj) {
        sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    public void update(BackupDatabase obj) {
        sessionFactory.getCurrentSession().update(obj);
    }

    @Override
    public BackupDatabase getById(Integer id) {
        return (BackupDatabase) sessionFactory.getCurrentSession().get(BackupDatabase.class, id);
    }

    @Override
    public List<BackupDatabase> getAll() {
        return (List<BackupDatabase>) sessionFactory.getCurrentSession().createCriteria(BackupDatabase.class).list();
    }
}
