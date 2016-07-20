package models;

import javax.persistence.*;

@Entity
@Table(name="archive_database")
public class ArchiveDatabase extends ArchiveBase {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "backup_database_id")
    private BackupDatabase backupDatabase;

    public BackupDatabase getBackupDatabase() {
        return backupDatabase;
    }

    public void setBackupDatabase(BackupDatabase backupDatabase) {
        this.backupDatabase = backupDatabase;
    }
}
