package dao;


import models.File;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileDAO implements DAOInterface<File> {

    @Autowired
    private SessionFactory sessionFactory;

    public FileDAO() {
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(File file) {
        sessionFactory.getCurrentSession().save(file);
    }

    public void save(File file) {
        sessionFactory.getCurrentSession().update(file);
    }

    public File getFile(Integer id) {
        return (File) sessionFactory.getCurrentSession().get(File.class, id);
    }

    @Override
    public List<File> getAll() {
        return (List<File>) sessionFactory.getCurrentSession().createCriteria(File.class).list();
    }
}
