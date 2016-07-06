package services;

import dao.FileDAO;
import models.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileDAO fileDAO;

    public void setFileDAO(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    @Transactional
    public void save(File file) {
        fileDAO.save(file);
    }

    @Transactional
    public File getFile(Integer fileId) {
        return fileDAO.getFile(fileId);
    }

    @Transactional
    public List<File> getAll() {
        return fileDAO.getAll();
    }
}
