package helpers;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import config.Settings;
import helpers.database.DatabaseConnectionInterface;

import java.io.IOException;

public class SshDatabaseBackuper extends SshConnect {
    private DatabaseConnectionInterface dbConnection;

    public SshDatabaseBackuper(String address, int port, String user, String password, DatabaseConnectionInterface dbConnection) throws JSchException {
        super(address, port, user, password);
        this.dbConnection = dbConnection;
        sshConnect();
    }

    public Integer getTablesCount() throws IOException, JSchException {
        String result = executeBash(dbConnection.getTablesCountCommand());
        try {
            return Integer.parseInt(result);
        } catch (Exception e) {
            return null;
        }
    }

    public String createDatabaseBackup() throws JSchException, IOException, SftpException {
        String result = executeBash(dbConnection.getDumpCommand());
        String archivePath = createArchive(dbConnection.getDbName(), dbConnection.getTempBackupName());
        return downloadBackup(archivePath, Settings.getAppHome()+"/"+getArchiveFullName(dbConnection.getDbName()));
    }
}
