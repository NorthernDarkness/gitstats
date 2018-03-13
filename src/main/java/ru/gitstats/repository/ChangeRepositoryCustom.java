package ru.gitstats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.gitstats.model.Change;

@Transactional
public interface ChangeRepositoryCustom extends JpaRepository<Change, Long> {

    @Query(value = "INSERT INTO changes (LINES_ADDED, LINES_DELETED, COMMIT_ID, FILE_ID) VALUES (?0 ,?1, ?3 , " +
            "(SELECT ID FROM files WHERE PATH = ?4));", nativeQuery = true)
    void insertIfFileNotExist(long linesAdded, long linesDeleted, long commitId, String path);
}
