package helpers;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import config.Settings;
import models.BackupFiles;

import java.io.IOException;

public class SshFilesBackup extends SshConnect {
    private BackupFiles backupFiles;

    public SshFilesBackup(String address, int port, String user, String password, BackupFiles backupFiles) throws JSchException {
        super(address, port, user, password);
        this.backupFiles = backupFiles;
        sshConnect();
    }

    public String createFilesBackup(String name) throws IOException, JSchException, SftpException {
        String archivePath = tempDir+"/"+getArchiveFullName(name);

        StringBuilder sb = new StringBuilder();
        sb
                .append("tar -zcf \"")
                .append(archivePath)
                .append("\" ");
        String[] ignoreFiles = backupFiles.getIgnoreFiles().split("\n");
        for (String ignoreFile : ignoreFiles) {
            // TODO: delete last "/" char
            sb.append("--exclude=").append(ignoreFile).append(" ");
        }
        sb.append(backupFiles.getFolder());

        String command = sb.toString();
        String finalCommand = command.replaceAll("\r", "");
        System.out.println(finalCommand);
        executeBash(finalCommand);
        return downloadBackup(archivePath, Settings.getAppHome()+"/"+getArchiveFullName(backupFiles.getTitle()));
    }
}
