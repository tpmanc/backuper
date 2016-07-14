package models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="server")
public class Server {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="url")
    private String url;

    @Column(name="sftp_user")
    private String sftpUser;

    @Column(name="sftp_password")
    private String sftpPassword;

    @Column(name="sftp_port")
    private int sftpPort;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "server", cascade = CascadeType.ALL)
    private Set<BackupDatabase> backupsDatabase;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "server", cascade = CascadeType.ALL)
    private Set<BackupFiles> backupsFiles;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSftpUser() {
        return sftpUser;
    }

    public void setSftpUser(String sftpUser) {
        this.sftpUser = sftpUser;
    }

    public String getSftpPassword() {
        return sftpPassword;
    }

    public void setSftpPassword(String sftpPassword) {
        this.sftpPassword = sftpPassword;
    }

    public int getSftpPort() {
        return sftpPort;
    }

    public void setSftpPort(int sftpPort) {
        this.sftpPort = sftpPort;
    }

    public Set<BackupDatabase> getBackupsDatabase() {
        return backupsDatabase;
    }

    public void setBackupsDatabase(Set<BackupDatabase> backupsDatabase) {
        this.backupsDatabase = backupsDatabase;
    }

    public Set<BackupFiles> getBackupsFiles() {
        return backupsFiles;
    }

    public void setBackupsFiles(Set<BackupFiles> backupsFiles) {
        this.backupsFiles = backupsFiles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
