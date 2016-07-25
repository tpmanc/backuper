package schedulers;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import exceptions.BashExecuteException;
import helpers.BackupStatus;
import helpers.HashHelper;
import helpers.SshDatabaseBackup;
import helpers.SshFilesBackup;
import helpers.database.DatabaseConnectionInterface;
import helpers.database.MysqlConnection;
import helpers.database.PostgresqlConnection;
import models.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import services.ArchiveDatabaseService;
import services.ArchiveFilesService;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BackupScheduler {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public ArchiveDatabaseService archiveDatabaseService;

    @Autowired
    public ArchiveFilesService archiveFilesService;

    public void databaseBackupRunner() {
        List<ArchiveDatabase> waitnig = archiveDatabaseService.getWaiting();
        for (ArchiveDatabase archive : waitnig) {
            archive.setStatus(BackupStatus.STATUS_IN_PROCESS);
            archiveDatabaseService.update(archive);

            Session session = sessionFactory.openSession();
            archive = (ArchiveDatabase) session.merge(archive);
            Hibernate.initialize(archive.getBackupDatabase().getServer());
            session.close();
            BackupDatabase backupDatabase = archive.getBackupDatabase();
            Server server = backupDatabase.getServer();

            DatabaseConnectionInterface dbConnection;
            if (backupDatabase.getDatabaseType() == 1) {//3301
                dbConnection = new MysqlConnection(backupDatabase.getDatabaseUser(), backupDatabase.getDatabasePassword(), backupDatabase.getDatabaseName(), backupDatabase.getDatabasePort());
            } else if (backupDatabase.getDatabaseType() == 2) {//5432
                dbConnection = new PostgresqlConnection(backupDatabase.getDatabaseUser(), backupDatabase.getDatabasePassword(), backupDatabase.getDatabaseName(), backupDatabase.getDatabasePort());
            } else {
                dbConnection = null;
            }

            try {
                SshDatabaseBackup sshDatabaseBackup = new SshDatabaseBackup(server.getHost(), server.getSshPort(), server.getSshUser(), server.getSshPassword(), dbConnection);
                String filePath = sshDatabaseBackup.createDatabaseBackup();

                File file = new File(filePath);
                String hash = HashHelper.getHash(filePath);
                double fileSize = file.length(); // bytes
                File newFile = new File(HashHelper.getHashDir(hash) + file.getName());
                file.renameTo(newFile);
                archive.setName(file.getName());
                archive.setHash(hash);
                archive.setSize(fileSize);
                archive.setStatus(BackupStatus.STATUS_SUCCESS);
            } catch (JSchException e) {
                e.printStackTrace();
                archive.setStatus(BackupStatus.STATUS_ERROR);
                archive.setMessage(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                archive.setStatus(BackupStatus.STATUS_ERROR);
                archive.setMessage(e.getMessage());
            } catch (SftpException e) {
                e.printStackTrace();
                archive.setStatus(BackupStatus.STATUS_ERROR);
                archive.setMessage(e.getMessage());
            } catch (BashExecuteException e) {
                archive.setStatus(BackupStatus.STATUS_ERROR);
                archive.setMessage(e.getMessage());
            } finally {
                archiveDatabaseService.update(archive);
            }
        }
    }

    public void filesBackupRunner() {
        List<ArchiveFiles> waitnig = archiveFilesService.getWaiting();
        for (ArchiveFiles archive : waitnig) {
            archive.setStatus(BackupStatus.STATUS_IN_PROCESS);
            archiveFilesService.update(archive);

            Session session = sessionFactory.openSession();
            archive = (ArchiveFiles) session.merge(archive);
            Hibernate.initialize(archive.getBackupFiles().getServer());
            session.close();
            BackupFiles backupFiles = archive.getBackupFiles();
            Server server = backupFiles.getServer();

            try {
                SshFilesBackup sshFilesBackup = new SshFilesBackup(server.getHost(), server.getSshPort(), server.getSshUser(), server.getSshPassword(), backupFiles);
                String filePath = sshFilesBackup.createFilesBackup(backupFiles.getTitle());
                sshFilesBackup.disconnect();

                File file = new File(filePath);
                String hash = HashHelper.getHash(filePath);
                double fileSize = file.length(); // bytes
                File newFile = new File(HashHelper.getHashDir(hash) + file.getName());
                file.renameTo(newFile);
                archive.setName(file.getName());
                archive.setHash(hash);
                archive.setSize(fileSize);
                archive.setStatus(BackupStatus.STATUS_SUCCESS);
            } catch (JSchException e) {
                e.printStackTrace();
                archive.setStatus(BackupStatus.STATUS_ERROR);
                archive.setMessage(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                archive.setStatus(BackupStatus.STATUS_ERROR);
                archive.setMessage(e.getMessage());
            } catch (SftpException e) {
                e.printStackTrace();
                archive.setStatus(BackupStatus.STATUS_ERROR);
                archive.setMessage(e.getMessage());
            } catch (BashExecuteException e) {
                e.printStackTrace();
                archive.setStatus(BackupStatus.STATUS_ERROR);
                archive.setMessage(e.getMessage());
            } finally {
                archiveFilesService.update(archive);
            }
        }
    }
}
