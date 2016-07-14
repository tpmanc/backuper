package controllers;

import authentication.CustomUser;
import exceptions.ForbiddenException;
import exceptions.NotFoundException;
import helpers.UserHelper;
import models.BackupDatabase;
import models.BackupFiles;
import models.Server;
import models.User;
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
import services.ServerService;
import services.UserService;

import java.security.Principal;
import java.util.*;

@Controller
public class ServerController {
    @Autowired
    public ServerService serverService;

    @Autowired
    public UserService userService;

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value = {"/servers"}, method = RequestMethod.GET)
    public String sites(
            Model model,
            Principal principal
    ) {
        CustomUser customUser = UserHelper.getCustomUser(principal);
        User user = userService.getById(customUser.getId());
        String title = "Servers";
        List<Server> servers = serverService.getAllByUserId(user);

        Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
        breadcrumbs.put("Servers", null);
        model.addAttribute("breadcrumbs", breadcrumbs);

        model.addAttribute("servers", servers);
        model.addAttribute("title", title);

        return "server/servers";
    }

    @RequestMapping(value = {"/server/{id}"}, method = RequestMethod.GET)
    public String site(
            @PathVariable int id,
            Model model,
            Principal principal
    ) {
        CustomUser customUser = UserHelper.getCustomUser(principal);
        Server server = serverService.getById(id);
        if (server == null) {
            throw new NotFoundException("Page Not Found");
        }
        model.addAttribute(server);

        Session session = sessionFactory.openSession();
        server = (Server) session.merge(server);
        Hibernate.initialize(server.getUser());
        Hibernate.initialize(server.getBackupsDatabase());
        Hibernate.initialize(server.getBackupsFiles());
        session.close();

        if (customUser.getId() != server.getUser().getId()) {
            throw new ForbiddenException("Forbidden");
        }

        Set<BackupDatabase> backupsDatabase = server.getBackupsDatabase();
        model.addAttribute("backupsDatabase", backupsDatabase);

        Set<BackupFiles> backupFiles = server.getBackupsFiles();
        model.addAttribute("backupFiles", backupFiles);

        String title = "Server: "+server.getTitle();
        Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
        breadcrumbs.put("Servers", "/servers");
        breadcrumbs.put(title, null);
        model.addAttribute("breadcrumbs", breadcrumbs);

        model.addAttribute("title", title);
        return "server/server";
    }

    @RequestMapping(value = {"/server/add"}, method = RequestMethod.GET)
    public String siteAdd(
            Model model
    ) {
        String title = "Add New Server";
        Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
        breadcrumbs.put("Servers", "/servers");
        breadcrumbs.put(title, null);

        model.addAttribute("breadcrumbs", breadcrumbs);

        model.addAttribute("title", title);
        return "server/server-add";
    }

    @RequestMapping(value = {"/server/add/handler"}, method = RequestMethod.POST)
    public String siteAddHandler(
            @RequestParam String title,
            @RequestParam String url,
            @RequestParam String sftpUser,
            @RequestParam String sftpPassword,
            @RequestParam Integer sftpPort,
            Principal principal
    ) {
        CustomUser customUser = UserHelper.getCustomUser(principal);
        User user = userService.getById(customUser.getId());
        Server server = new Server();
        server.setUser(user);
        server.setTitle(title);
        server.setUrl(url);
        server.setSftpUser(sftpUser);
        server.setSftpPassword(sftpPassword);
        server.setSftpPort(sftpPort);
        serverService.create(server);
        return "redirect:/servers";
    }
}
