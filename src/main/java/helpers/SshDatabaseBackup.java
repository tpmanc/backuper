package helpers;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import config.Settings;
import helpers.database.DatabaseConnectionInterface;

import java.io.IOException;

public class SshDatabaseBackup extends SshConnect {
    private DatabaseConnectionInterface dbConnection;

    public SshDatabaseBackup(String address, int port, String user, String password, DatabaseConnectionInterface dbConnection) throws JSchException {
        super(address, port, user, password);
        this.dbConnection = dbConnection;
        sshConnect();
    }

    public String createDatabaseBackup() throws JSchException, IOException, SftpException {
        String result = executeBash(dbConnection.getDumpCommand());
        String archivePath = createArchive(dbConnection.getDbName(), dbConnection.getTempBackupName());
        return downloadBackup(archivePath, Settings.getAppHome()+"/"+getArchiveFullName(dbConnection.getDbName()));
    }
}
