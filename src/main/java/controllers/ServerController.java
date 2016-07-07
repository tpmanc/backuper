package controllers;

import exceptions.NotFoundException;
import models.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.ServerService;

import java.util.List;

@Controller
public class ServerController {
    @Autowired
    public ServerService serverService;

    @RequestMapping(value = {"/servers"}, method = RequestMethod.GET)
    public String sites(
            Model model
    ) {
        List<Server> servers = serverService.getAll();
        model.addAttribute("servers", servers);
        model.addAttribute("title", "Index page");
        return "server/servers";
    }

    @RequestMapping(value = {"/server"}, method = RequestMethod.GET)
    public String site(
            @RequestParam int id,
            Model model
    ) {
        Server server = serverService.getSite(id);
        if (server == null) {
            throw new NotFoundException("Page Not Found");
        }
        model.addAttribute("title", "Index page");
        return "server/server";
    }

    @RequestMapping(value = {"/server/add"}, method = RequestMethod.GET)
    public String siteAdd(
            Model model
    ) {
        model.addAttribute("title", "Index page");
        return "server/server-add";
    }

    @RequestMapping(value = {"/server/add/handler"}, method = RequestMethod.POST)
    public String siteAddHandler(
            @RequestParam String title,
            @RequestParam String url,
            @RequestParam String sftpUser,
            @RequestParam String sftpPassword,
            @RequestParam Integer sftpPort
    ) {
        Server server = new Server();
        server.setTitle(title);
        server.setUrl(url);
        server.setSftpUser(sftpUser);
        server.setSftpPassword(sftpPassword);
        server.setSftpPort(sftpPort);
        serverService.create(server);
        return "redirect:/servers";
    }
}
