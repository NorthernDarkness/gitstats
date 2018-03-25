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
public class Change {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "lines_added", nullable = false)
    private long linesAdded;

    @Column(name = "lines_deleted", nullable = false)
    private long linesDeleted;

    @Column(name = "file", nullable = false)
    private String file;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "files_changes", joinColumns = {
//            @JoinColumn(name = "change_id", nullable = false, updatable = false) },
//            inverseJoinColumns = { @JoinColumn(name = "file_id",
//                    nullable = false, updatable = false) })
//    private Set<File> files;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commit_id")
    private Commit commit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Change change = (Change) o;

        if (id != change.id) return false;
        if (linesAdded != change.linesAdded) return false;
        if (linesDeleted != change.linesDeleted) return false;
        return file.equals(change.file);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (linesAdded ^ (linesAdded >>> 32));
        result = 31 * result + (int) (linesDeleted ^ (linesDeleted >>> 32));
        result = 31 * result + file.hashCode();
        return result;
    }
}
