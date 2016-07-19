package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="backup_files")
public class BackupFiles {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="folder", nullable = false)
    private String folder;

    @Column(name="ignore_files", nullable = false)
    private String ignoreFiles;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id")
    private Server server;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "backupFiles", cascade = CascadeType.ALL)
    @OrderBy("date desc")
    private Set<ArchiveFiles> archiveFiles;

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

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getIgnoreFiles() {
        return ignoreFiles;
    }

    public void setIgnoreFiles(String ignoreFiles) {
        this.ignoreFiles = ignoreFiles;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Set<ArchiveFiles> getArchiveFiles() {
        return archiveFiles;
    }

    public void setArchiveFiles(Set<ArchiveFiles> archiveFiles) {
        this.archiveFiles = archiveFiles;
    }
}
