package services;


import dao.BackupDatabaseDAO;
import models.BackupDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class BackupDatabaseService {
    @Autowired
    private BackupDatabaseDAO backupDatabaseDAO;

    public void setBackupDatabaseDAO(BackupDatabaseDAO dao) {
        this.backupDatabaseDAO = dao;
    }

    @Transactional
    public void create(BackupDatabase server) {
        backupDatabaseDAO.create(server);
    }

    @Transactional
    public void update(BackupDatabase server) {
        backupDatabaseDAO.update(server);
    }

    @Transactional
    public BackupDatabase getById(Integer id) {
        return backupDatabaseDAO.getById(id);
    }

    @Transactional
    public List<BackupDatabase> getAll() {
        return backupDatabaseDAO.getAll();
    }
}
