package controllers;

import com.jcraft.jsch.*;
import config.IsFilled;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ContextLoader;

@Controller
public class IndexController {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index(
            Model model
    ) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IsFilled isFilled = (IsFilled) ctx.getBean("isFilled");
        if (!isFilled.isFilled()) {
//            return "redirect:/init-settings";
        }

        Session session = null;
        Channel channel = null;
//        try {>
//            JSch ssh = new JSch();
//            session = ssh.getSession("user", "95.165.144.117", 665);
//            session.setPassword("Pa$$w0rd");
//
//            java.util.Properties config = new java.util.Properties();
//            config.put("StrictHostKeyChecking", "no");
//            session.setConfig(config);
//
//            session.connect();
//            channel = session.openChannel("sftp");
//            channel.connect();
//            ChannelSftp sftp = (ChannelSftp) channel;
//            sftp.lcd("/Users/tpmanc/Downloads/");
//            sftp.get("/home/user/www/chukancev.ru/requirements.php", "/Users/tpmanc/Downloads/requirements.php");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (channel != null) {
//                channel.disconnect();
//            }
//            if (session != null) {
//                session.disconnect();
//            }
//        }

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

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(
            Model model
    ) {
        model.addAttribute("title", "Login");
        return "index/login";
    }
}
