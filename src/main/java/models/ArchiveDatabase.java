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
    private int name;

    @Column(name="hash", nullable = false)
    private String hash;

    @Column(name="size", nullable = false)
    private long size;

    @Column(name="date", nullable = false)
    private long date;

    @Column(name="table_count", nullable = false)
    private int tableCount;

    @Column(name="for_delete", nullable = false)
    private boolean forDelete;
}
