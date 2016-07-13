package models;

import javax.persistence.*;

@Entity
@Table(name="archive_database")
public class ArchiveDatabase {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="hash", nullable = false)
    private String hash;

    @Column(name="size", nullable = false)
    private double size;

    @Column(name="date", nullable = false)
    private long date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "backup_database_id")
    private BackupDatabase backupDatabase;

    @Column(name="for_delete", nullable = false)
    private boolean forDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public BackupDatabase getBackupDatabase() {
        return backupDatabase;
    }

    public void setBackupDatabase(BackupDatabase backupDatabase) {
        this.backupDatabase = backupDatabase;
    }

    public boolean isForDelete() {
        return forDelete;
    }

    public void setForDelete(boolean forDelete) {
        this.forDelete = forDelete;
    }
}
