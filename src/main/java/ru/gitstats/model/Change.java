package ru.gitstats.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "changes")
@Getter
@Setter
@EqualsAndHashCode
public class Change {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "LINES_ADDED", nullable = false)
    private long linesAdded;

    @Column(name = "LINES_DELETED", nullable = false)
    private long linesDeleted;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "files_changes", joinColumns = {
            @JoinColumn(name = "CHANGE_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "FILE_ID",
                    nullable = false, updatable = false) })
    private Set<File> files;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMIT_ID", nullable = false)
    private Commit commit;
}
