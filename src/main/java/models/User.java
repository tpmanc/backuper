package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="email")
    private String email;

    @Column(name="password_hash")
    private String passwordHash;

    @Column(name="salt")
    private String salt;

    @Column(name="backup_files_limit")
    private int backupFilesLimit;

    @Column(name="backup_database_limit")
    private int backupDatabaseLimit;

    @Column(name="is_disabled")
    private boolean isDisabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Server> servers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public Set<Server> getServers() {
        return servers;
    }

    public void setServers(Set<Server> servers) {
        this.servers = servers;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getBackupFilesLimit() {
        return backupFilesLimit;
    }

    public void setBackupFilesLimit(int backupFilesLimit) {
        this.backupFilesLimit = backupFilesLimit;
    }

    public int getBackupDatabaseLimit() {
        return backupDatabaseLimit;
    }

    public void setBackupDatabaseLimit(int backupDatabaseLimit) {
        this.backupDatabaseLimit = backupDatabaseLimit;
    }
}
