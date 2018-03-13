package ru.gitstats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.gitstats.model.Change;
import ru.gitstats.model.File;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public interface ChangeRepositoryImpl extends ChangeRepositoryCustom {

    @Query(value = ";", nativeQuery = true)
    void insertIfFileNotExist(long linesAdded, long linesDeleted, long commitId, String path);

    @PersistenceContext
    EntityManager entityManager;

    public Change save(Change change){
        final javax.persistence.Query query = entityManager.createNativeQuery("INSERT INTO changes (LINES_ADDED, LINES_DELETED, COMMIT_ID, FILE_ID) VALUES (?0 ,?1, ?3 , " +
                        "(SELECT ID FROM files WHERE PATH = ?4))");
        query.setParameter(0, change.getLinesAdded());
        query.setParameter(1, change.getLinesDeleted());
        query.setParameter(2, change.getLinesAdded());
        query.setParameter(4, change.getLinesAdded());
        query.executeUpdate();
        return file;
    }
}
