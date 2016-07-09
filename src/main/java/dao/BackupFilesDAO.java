package dao;

import models.BackupDatabase;
import models.BackupFiles;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BackupFilesDAO implements DAOInterface<BackupFiles> {
    @Autowired
    private SessionFactory sessionFactory;

    public BackupFilesDAO() {
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(BackupFiles obj) {
        sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    public void update(BackupFiles obj) {
        sessionFactory.getCurrentSession().update(obj);
    }

    @Override
    public BackupFiles getById(Integer id) {
        return (BackupFiles) sessionFactory.getCurrentSession().get(BackupFiles.class, id);
    }

    @Override
    public List<BackupFiles> getAll() {
        return (List<BackupFiles>) sessionFactory.getCurrentSession().createCriteria(BackupFiles.class).list();
    }
}
