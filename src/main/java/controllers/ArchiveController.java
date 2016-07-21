package controllers;

import exceptions.NotFoundException;
import models.ArchiveFiles;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.ArchiveDatabaseService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.ArchiveDatabaseService;
import models.ArchiveDatabase;
import helpers.HashHelper;
import services.ArchiveFilesService;

import java.io.*;
import java.sql.SQLException;

@Controller
public class ArchiveController {
    @Autowired
    public ArchiveDatabaseService archiveDatabaseService;

    @Autowired
    public ArchiveFilesService archiveFilesService;

    @RequestMapping(value = {"/archive/download/database/{id}" }, method = RequestMethod.GET)
    public void databaseDownload(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            ArchiveDatabase archive = archiveDatabaseService.getById(id);

            StringBuilder filePath = new StringBuilder();
            filePath.append(HashHelper.getHashDir(archive.getHash()));
            filePath
                    .append(File.separator)
                    .append(archive.getName());
            File file = new File(filePath.toString());
            InputStream inputStream = new FileInputStream(file);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + archive.getName() + "\"");
            OutputStream os = response.getOutputStream();
            FileCopyUtils.copy(inputStream, os);
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            throw new NotFoundException("Файла не найден");
        } catch (IOException e) {
            throw new NotFoundException("Ошибка", "500");
        }
    }

    @RequestMapping(value = {"/archive/download/files/{id}" }, method = RequestMethod.GET)
    public void filesDownload(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            ArchiveFiles archive = archiveFilesService.getById(id);

            StringBuilder filePath = new StringBuilder();
            filePath.append(HashHelper.getHashDir(archive.getHash()));
            filePath
                    .append(File.separator)
                    .append(archive.getName());
            File file = new File(filePath.toString());
            InputStream inputStream = new FileInputStream(file);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + archive.getName() + "\"");
            OutputStream os = response.getOutputStream();
            FileCopyUtils.copy(inputStream, os);
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            throw new NotFoundException("Файла не найден");
        } catch (IOException e) {
            throw new NotFoundException("Ошибка", "500");
        }
    }
}
