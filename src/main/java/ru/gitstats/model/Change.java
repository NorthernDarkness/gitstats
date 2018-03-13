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
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "LINES_ADDED", nullable = false)
    private long linesAdded;

    @Column(name = "LINES_DELETED", nullable = false)
    private long linesDeleted;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "files_changes", joinColumns = {
            @JoinColumn(name = "CHANGE_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "FILE_ID",
                    nullable = false, updatable = false) })
    private Set<File> files;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMIT_ID", nullable = false)
    private Commit commit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Change change = (Change) o;

        if (id != change.id) return false;
        if (linesAdded != change.linesAdded) return false;
        return linesDeleted == change.linesDeleted;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (linesAdded ^ (linesAdded >>> 32));
        result = 31 * result + (int) (linesDeleted ^ (linesDeleted >>> 32));
        return result;
    }
}
