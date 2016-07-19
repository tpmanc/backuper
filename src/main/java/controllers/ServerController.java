package controllers;

import authentication.CustomUser;
import exceptions.ForbiddenException;
import exceptions.NotFoundException;
import helpers.UserHelper;
import helpers.ValidationHelper;
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
import org.springframework.validation.*;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import services.ServerService;
import services.UserService;
import validators.ServerValidator;

import java.lang.reflect.Array;
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
    public String servers(
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
    public String server(
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
    public String serverAdd(
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

    @RequestMapping(value = {"/server/edit/{id}"}, method = RequestMethod.GET)
    public String serverEdit(
            @PathVariable int id,
            Model model
    ) {
        Server server = serverService.getById(id);
        if (server == null) {
            throw new NotFoundException("Server Not Found");
        }
        model.addAttribute(server);
        String title = "Edit";
        Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
        breadcrumbs.put("Servers", "/servers");
        breadcrumbs.put("Server: " + server.getTitle(), "/server/" + server.getId());
        breadcrumbs.put(title, null);

        model.addAttribute("breadcrumbs", breadcrumbs);

        model.addAttribute("title", title);
        return "server/server-edit";
    }

    @RequestMapping(value = {"/server/save/handler"}, method = RequestMethod.POST)
    public String serverAddHandler(
            @RequestParam(value="serverId", required=false) Integer serverId,
            @RequestParam String title,
            @RequestParam String url,
            @RequestParam String sshUser,
            @RequestParam String sshPassword,
            @RequestParam Integer sshPort,
            RedirectAttributes attr,
            Principal principal
    ) {
        if (serverId == null) {
            CustomUser customUser = UserHelper.getCustomUser(principal);
            User user = userService.getById(customUser.getId());
            Server server = new Server();
            server.setUser(user);
            server.setTitle(title);
            server.setHost(url);
            server.setSshUser(sshUser);
            server.setSshPassword(sshPassword);
            server.setSshPort(sshPort);
            Map<String, ArrayList<String>> errors = ValidationHelper.validate(server, new ServerValidator());
            if (errors.size() > 0) {
                attr.addFlashAttribute("server", server);
                attr.addFlashAttribute("errors", errors);
                return "redirect:/server/add";
            } else {
                serverService.create(server);
                return "redirect:/servers";
            }
        } else {
            Server server = serverService.getById(serverId);
            if (server == null) {
                throw new NotFoundException("Server Not Found");
            }
            server.setTitle(title);
            server.setHost(url);
            server.setSshUser(sshUser);
            server.setSshPassword(sshPassword);
            server.setSshPort(sshPort);

            Map<String, ArrayList<String>> errors = ValidationHelper.validate(server, new ServerValidator());
            if (errors.size() > 0) {
                attr.addFlashAttribute("errors", errors);
                return "redirect:/server/edit/" + server.getId();
            } else {
                serverService.update(server);
                return "redirect:/servers";
            }
        }
    }
}
