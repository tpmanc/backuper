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
    private long size;

    @Column(name="date", nullable = false)
    private long date;

    @Column(name="table_count", nullable = false)
    private int tableCount;

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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getTableCount() {
        return tableCount;
    }

    public void setTableCount(int tableCount) {
        this.tableCount = tableCount;
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
