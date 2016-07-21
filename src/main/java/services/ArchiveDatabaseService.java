package services;

import dao.ArchiveDatabaseDAO;
import models.ArchiveDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class ArchiveDatabaseService {
    @Autowired
    private ArchiveDatabaseDAO dao;

    public void setDao(ArchiveDatabaseDAO dao) {
        this.dao = dao;
    }

    @Transactional
    public void create(ArchiveDatabase obj) {
        obj.setDate(new Date().getTime());
        dao.create(obj);
    }

    @Transactional
    public void update(ArchiveDatabase obj) {
        dao.update(obj);
    }

    @Transactional
    public ArchiveDatabase getById(Integer id) {
        return dao.getById(id);
    }

    @Transactional
    public List<ArchiveDatabase> getAll() {
        return dao.getAll();
    }

    @Transactional
    public List<ArchiveDatabase> getWaiting() {
        return dao.getWaiting();
    }
}
