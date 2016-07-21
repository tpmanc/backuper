package services;

import dao.ArchiveFilesDAO;
import models.ArchiveFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class ArchiveFilesService {
    @Autowired
    private ArchiveFilesDAO dao;

    public void setDao(ArchiveFilesDAO dao) {
        this.dao = dao;
    }

    @Transactional
    public void create(ArchiveFiles obj) {
        obj.setDate(new Date().getTime());
        dao.create(obj);
    }

    @Transactional
    public void update(ArchiveFiles obj) {
        dao.update(obj);
    }

    @Transactional
    public ArchiveFiles getById(Integer id) {
        return dao.getById(id);
    }

    @Transactional
    public List<ArchiveFiles> getAll() {
        return dao.getAll();
    }

    @Transactional
    public List<ArchiveFiles> getWaiting() {
        return dao.getWaiting();
    }
}
