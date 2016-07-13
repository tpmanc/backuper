package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="backup_database")
public class BackupDatabase {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="database_type", nullable = false)
    private int databaseType;

    @Column(name="database_name", nullable = false)
    private String databaseName;

    @Column(name="database_user", nullable = false)
    private String databaseUser;

    @Column(name="database_password", nullable = false)
    private String databasePassword;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id")
    private Server server;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "backupDatabase", cascade = CascadeType.ALL)
    @OrderBy("date desc")
    private Set<ArchiveDatabase> archiveDatabases;

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

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseUser() {
        return databaseUser;
    }

    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public int getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(int databaseType) {
        this.databaseType = databaseType;
    }

    public Set<ArchiveDatabase> getArchiveDatabases() {
        return archiveDatabases;
    }

    public void setArchiveDatabases(Set<ArchiveDatabase> archiveDatabases) {
        this.archiveDatabases = archiveDatabases;
    }
}
