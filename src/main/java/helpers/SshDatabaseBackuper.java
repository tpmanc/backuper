package helpers;

import com.jcraft.jsch.JSchException;
import helpers.database.DatabaseConnectionInterface;

import java.io.IOException;

public class SshDatabaseBackuper extends SshConnect {
    public SshDatabaseBackuper(String address, int port, String user, String password) {
        super(address, port, user, password);
    }

    public void createDatabaseBackup(DatabaseConnectionInterface dbConnection) throws JSchException, IOException {
        sshConnect();
    }
}
