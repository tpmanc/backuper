package controllers;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import exceptions.InternalException;
import exceptions.NotFoundException;
import helpers.HashHelper;
import helpers.SshDatabaseBackuper;
import helpers.database.MysqlConnection;
import models.ArchiveDatabase;
import models.BackupDatabase;
import models.BackupFiles;
import models.Server;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.ArchiveDatabaseService;
import services.BackupDatabaseService;
import services.BackupFilesService;
import services.ServerService;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class BackupController {
    @Autowired
    public ServerService serverService;

    @Autowired
    public BackupDatabaseService backupDatabaseService;

    @Autowired
    public BackupFilesService backupFilesService;

    @Autowired
    public ArchiveDatabaseService archiveDatabaseService;

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value = {"/backup/database/{id}"}, method = RequestMethod.GET)
    public String backupDatabase(
            @PathVariable int id,
            Model model
    ) {
        BackupDatabase backupDatabase = backupDatabaseService.getById(id);
        if (backupDatabase == null) {
            throw new NotFoundException("Page Not Found");
        }
        model.addAttribute(backupDatabase);

        Session session = sessionFactory.openSession();
        backupDatabase = (BackupDatabase) session.merge(backupDatabase);
        Hibernate.initialize(backupDatabase.getServer());
        Hibernate.initialize(backupDatabase.getArchiveDatabases());
        session.close();

        Server server = backupDatabase.getServer();
        model.addAttribute(server);

        Set<ArchiveDatabase> archives = backupDatabase.getArchiveDatabases();
        model.addAttribute("archives", archives);

        String title = "Database Backup: "+backupDatabase.getTitle();
        Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
        breadcrumbs.put("Servers", "/servers");
        breadcrumbs.put("Server: "+server.getTitle(), "/server/"+server.getId());
        breadcrumbs.put(title, null);

        model.addAttribute("breadcrumbs", breadcrumbs);

        model.addAttribute("title", title);
        return "backup/backup-db";
    }

    @RequestMapping(value = {"/backup/files/{id}"}, method = RequestMethod.GET)
    public String backupFiles(
            @PathVariable int id,
            Model model
    ) {
        BackupFiles backupFiles = backupFilesService.getById(id);
        if (backupFiles == null) {
            throw new NotFoundException("Page Not Found");
        }
        model.addAttribute(backupFiles);

        Session session = sessionFactory.openSession();
        backupFiles = (BackupFiles) session.merge(backupFiles);
        Hibernate.initialize(backupFiles.getServer());
        session.close();

        Server server = backupFiles.getServer();
        model.addAttribute(server);

        String title = "Files Backup: "+backupFiles.getTitle();
        Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
        breadcrumbs.put("Servers", "/servers");
        breadcrumbs.put("Server: "+server.getTitle(), "/server/"+server.getId());
        breadcrumbs.put(title, null);

        model.addAttribute("breadcrumbs", breadcrumbs);

        model.addAttribute("title", title);
        return "backup/backup-files";
    }

    @RequestMapping(value = {"/backup/add"}, method = RequestMethod.GET)
    public String backupAdd(
            @RequestParam int id,
            Model model
    ) {
        Server server = serverService.getById(id);
        if (server == null) {
            throw new NotFoundException("Page Not Found");
        }
        model.addAttribute(server);

        String title = "Add New Backup";
        Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
        breadcrumbs.put("Servers", "/servers");
        breadcrumbs.put(title, null);

        model.addAttribute("breadcrumbs", breadcrumbs);

        model.addAttribute("title", title);
        return "backup/backup-add";
    }

    @RequestMapping(value = {"/backup/database/edit/{id}"}, method = RequestMethod.GET)
    public String backupDatabaseEdit(
            @PathVariable int id,
            Model model
    ) {
        BackupDatabase backupDatabase = backupDatabaseService.getById(id);
        if (backupDatabase == null) {
            throw new NotFoundException("Page Not Found");
        }
        Session session = sessionFactory.openSession();
        backupDatabase = (BackupDatabase) session.merge(backupDatabase);
        Hibernate.initialize(backupDatabase.getServer());
        session.close();
        model.addAttribute(backupDatabase);

        String title = "Edit";
        Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
        breadcrumbs.put("Servers", "/servers");
        breadcrumbs.put("Server: "+backupDatabase.getServer().getTitle(), "/server/"+backupDatabase.getServer().getId());
        breadcrumbs.put(backupDatabase.getTitle(), "/backup/database/"+backupDatabase.getId());
        breadcrumbs.put(title, null);

        model.addAttribute("breadcrumbs", breadcrumbs);

        model.addAttribute("title", title);
        return "backup/backup-db-edit";
    }

    @RequestMapping(value = {"/backup/files/edit/{id}"}, method = RequestMethod.GET)
    public String backupFilesEdit(
            @PathVariable int id,
            Model model
    ) {
        BackupFiles backupFiles = backupFilesService.getById(id);
        if (backupFiles == null) {
            throw new NotFoundException("Page Not Found");
        }
        Session session = sessionFactory.openSession();
        backupFiles = (BackupFiles) session.merge(backupFiles);
        Hibernate.initialize(backupFiles.getServer());
        session.close();
        model.addAttribute(backupFiles);

        String title = "Edit";
        Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
        breadcrumbs.put("Servers", "/servers");
        breadcrumbs.put("Server: "+backupFiles.getServer().getTitle(), "/server/"+backupFiles.getServer().getId());
        breadcrumbs.put(backupFiles.getTitle(), "/backup/files/"+backupFiles.getId());
        breadcrumbs.put(title, null);

        model.addAttribute("breadcrumbs", breadcrumbs);

        model.addAttribute("title", title);
        return "backup/backup-files-edit";
    }

    @RequestMapping(value = {"/backup/save/handler"}, method = RequestMethod.POST)
    public String siteAddHandler(
            @RequestParam int serverId,
            @RequestParam(value="backupId", required=false) Integer backupId,
            @RequestParam String title,
            @RequestParam(value="backupType", required=false) Integer backupType,
            @RequestParam(value="dbType", required=false) Integer dbType,
            @RequestParam(value="dbUser", required=false) String dbUser,
            @RequestParam(value="dbPassword", required=false) String dbPassword,
            @RequestParam(value="dbName", required=false) String dbName,
            @RequestParam(value="filesFolder", required=false) String filesFolder,
            @RequestParam(value="filesIgnore", required=false) String filesIgnore
    ) {
        Server server = serverService.getById(serverId);
        if (server == null) {
            throw new NotFoundException("Server Not Found");
        }
        if (backupType == 1) {
            if (backupId == null) {
                BackupDatabase model = new BackupDatabase();
                model.setServer(server);
                model.setTitle(title);
                model.setDatabaseType(dbType);
                model.setDatabaseUser(dbUser);
                model.setDatabasePassword(dbPassword);
                model.setDatabaseName(dbName);
                backupDatabaseService.create(model);
            } else {
                BackupDatabase model = backupDatabaseService.getById(backupId);
                if (model == null) {
                    throw new NotFoundException("Backup Not Found");
                }
                model.setTitle(title);
                model.setDatabaseType(dbType);
                model.setDatabaseUser(dbUser);
                model.setDatabasePassword(dbPassword);
                model.setDatabaseName(dbName);
                backupDatabaseService.update(model);
            }
        } else if (backupType == 2) {
            if (backupId == null) {
                BackupFiles model = new BackupFiles();
                model.setServer(server);
                model.setTitle(title);
                model.setFolder(filesFolder);
                model.setIgnoreFiles(filesIgnore);
                backupFilesService.create(model);
            } else {
                BackupFiles model = backupFilesService.getById(backupId);
                if (model == null) {
                    throw new NotFoundException("Backup Not Found");
                }
                model.setTitle(title);
                model.setFolder(filesFolder);
                model.setIgnoreFiles(filesIgnore);
                backupFilesService.update(model);
            }
        } else {
            throw new NotFoundException("Page Not Found");
        }
        return "redirect:/server/"+serverId;
    }

    @RequestMapping(value = {"/backup/database/run/{id}"}, method = RequestMethod.GET)
    public String backupDatabaseRun(
            @PathVariable int id
    ) {
        BackupDatabase backupDatabase = backupDatabaseService.getById(id);
        if (backupDatabase == null) {
            throw new NotFoundException("Page Not Found");
        }

        Session session = sessionFactory.openSession();
        backupDatabase = (BackupDatabase) session.merge(backupDatabase);
        Hibernate.initialize(backupDatabase.getServer());
        session.close();
        Server server = backupDatabase.getServer();

        MysqlConnection mysqlConnection = new MysqlConnection(backupDatabase.getDatabaseUser(), backupDatabase.getDatabasePassword(), backupDatabase.getDatabaseName(), 3301);
        try {
            SshDatabaseBackuper sshDatabaseBackuper = new SshDatabaseBackuper(server.getUrl(), server.getSftpPort(), server.getSftpUser(), server.getSftpPassword(), mysqlConnection);
            String filePath = sshDatabaseBackuper.createDatabaseBackup();

            File file = new File(filePath);
            String hash = HashHelper.getHash(filePath);
            double fileSize = file.length(); // bytes
            File newFile = new File(HashHelper.getHashDir(hash) + file.getName());
            file.renameTo(newFile);
            ArchiveDatabase model = new ArchiveDatabase();
            model.setName(file.getName());
            model.setHash(hash);
            model.setSize(fileSize);
            model.setBackupDatabase(backupDatabase);
            model.setForDelete(false);
            archiveDatabaseService.create(model);
        } catch (JSchException e) {
            e.printStackTrace();
            throw new InternalException("Cant create backup");
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalException("Cant create backup");
        } catch (SftpException e) {
            e.printStackTrace();
            throw new InternalException("Cant create backup");
        } finally {

        }



        return "redirect:/backup/database/"+backupDatabase.getId();
    }
}
