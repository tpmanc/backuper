package dao;

import helpers.BackupStatus;
import models.ArchiveFiles;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ArchiveFilesDAO implements DAOInterface<ArchiveFiles> {
    @Autowired
    private SessionFactory sessionFactory;

    public ArchiveFilesDAO() {}

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(ArchiveFiles obj) {
        sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    public void update(ArchiveFiles obj) {
        sessionFactory.getCurrentSession().update(obj);
    }

    @Override
    public ArchiveFiles getById(Integer id) {
        return (ArchiveFiles) sessionFactory.getCurrentSession().get(ArchiveFiles.class, id);
    }

    @Override
    public List<ArchiveFiles> getAll() {
        return (List<ArchiveFiles>) sessionFactory.
                getCurrentSession().
                createCriteria(ArchiveFiles.class).
                list();
    }

    public List<ArchiveFiles> getWaiting() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ArchiveFiles.class);
        return (List<ArchiveFiles>) criteria
                .add(Restrictions.eq("status", BackupStatus.STATUS_WAITING))
                .addOrder(Order.asc("date"))
                .setMaxResults(10)
                .list();
    }

}
