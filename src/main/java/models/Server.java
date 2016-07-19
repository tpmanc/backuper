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

    @Column(name="host")
    private String host;

    @Column(name="ssh_user")
    private String sshUser;

    @Column(name="ssh_password")
    private String sshPassword;

    @Column(name="ssh_port")
    private Integer sshPort;

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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSshUser() {
        return sshUser;
    }

    public void setSshUser(String sshUser) {
        this.sshUser = sshUser;
    }

    public String getSshPassword() {
        return sshPassword;
    }

    public void setSshPassword(String sshPassword) {
        this.sshPassword = sshPassword;
    }

    public Integer getSshPort() {
        return sshPort;
    }

    public void setSshPort(Integer sshPort) {
        this.sshPort = sshPort;
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
