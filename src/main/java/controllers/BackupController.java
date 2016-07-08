package controllers;

import exceptions.NotFoundException;
import models.BackupDatabase;
import models.Server;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import services.BackupDatabaseService;
import services.ServerService;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class BackupController {
    @Autowired
    public ServerService serverService;

    @Autowired
    public BackupDatabaseService backupDatabaseService;

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value = {"/backup"}, method = RequestMethod.GET)
    public String backup(
            @RequestParam int id,
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
        session.close();

        Server server = backupDatabase.getServer();
        model.addAttribute(server);

        String title = "Backup";
        Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
        breadcrumbs.put("Servers", "/servers");
        breadcrumbs.put("Server: "+server.getTitle(), "/server?id="+server.getId());
        breadcrumbs.put(title, null);

        model.addAttribute("breadcrumbs", breadcrumbs);

        model.addAttribute("title", title);
        return "backup/backup";
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

    @RequestMapping(value = {"/backup/add/handler"}, method = RequestMethod.POST)
    public @ResponseBody String siteAddHandler(
            @RequestParam int serverId,
            @RequestParam String title,
            @RequestParam int backupType,
            @RequestParam int dbType,
            @RequestParam String dbUser,
            @RequestParam String dbPassword,
            @RequestParam String dbName,
            @RequestParam String filesFolder,
            @RequestParam String filesIgnore
    ) {
        Server server = serverService.getById(serverId);
        if (server == null) {
            throw new NotFoundException("Server Not Found");
        }
        if (serverId == 1) {
            BackupDatabase model = new BackupDatabase();
            model.setTitle(title);
            model.setServer(server);
            model.setDatabaseType(dbType);
            model.setDatabaseUser(dbUser);
            model.setDatabasePassword(dbPassword);
            model.setDatabaseName(dbName);
            backupDatabaseService.create(model);
        } else if (serverId == 2) {

        } else {
            throw new NotFoundException("Page Not Found");
        }
        return "redirect:/server?id="+serverId;
    }
}
