package controllers;

import com.jcraft.jsch.*;
import models.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.FileService;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    public FileService fileService;

    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String index(
            @RequestParam(value="categoryId", required=false, defaultValue = "0") int categoryId,
            Model model
    ) {
        Session session = null;
        Channel channel = null;
        try {
            JSch ssh = new JSch();
//            ssh.setKnownHosts("/Users/tpmanc/Downloads/test");
            session = ssh.getSession("user", "95.165.144.117", 665);
            session.setPassword("Pa$$w0rd");

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftp = (ChannelSftp) channel;
            sftp.lcd("/Users/tpmanc/Downloads/");
            sftp.get("/home/user/www/chukancev.ru/requirements.php", "/Users/tpmanc/Downloads/requirements.php");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }

//        File file = fileService.getFile(2);
//
//        List<File> list = fileService.getAll();
//
//        File edit = list.get(13);
//        edit.setTitle("TEST");
//        fileService.save(edit);
//
//        model.addAttribute("title", "Index page");
        return "index/index";
    }
}
