package services;

import dao.ServerDAO;
import models.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServerService {

    @Autowired
    private ServerDAO serverDAO;

    public void setServerDAO(ServerDAO serverDAO) {
        this.serverDAO = serverDAO;
    }

    @Transactional
    public void create(Server server) {
        serverDAO.create(server);
    }

    @Transactional
    public void update(Server server) {
        serverDAO.update(server);
    }

    @Transactional
    public Server getById(Integer id) {
        return serverDAO.getById(id);
    }

    @Transactional
    public List<Server> getAll() {
        return serverDAO.getAll();
    }
}
