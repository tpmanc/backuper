package services;

import dao.BackupFilesDAO;
import models.BackupFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class BackupFilesService {
    @Autowired
    private BackupFilesDAO backupFilesDAO;

    public void setBackupDatabaseDAO(BackupFilesDAO dao) {
        this.backupFilesDAO = dao;
    }

    @Transactional
    public void create(BackupFiles server) {
        backupFilesDAO.create(server);
    }

    @Transactional
    public void update(BackupFiles server) {
        backupFilesDAO.update(server);
    }

    @Transactional
    public BackupFiles getById(Integer id) {
        return backupFilesDAO.getById(id);
    }

    @Transactional
    public List<BackupFiles> getAll() {
        return backupFilesDAO.getAll();
    }
}
