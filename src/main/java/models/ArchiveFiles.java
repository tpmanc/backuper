package models;

import javax.persistence.*;

@Entity
@Table(name="archive_files")
public class ArchiveFiles extends ArchiveBase {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "backup_files_id")
    private BackupFiles backupFiles;

    public BackupFiles getBackupFiles() {
        return backupFiles;
    }

    public void setBackupFiles(BackupFiles backupFiles) {
        this.backupFiles = backupFiles;
    }
}
