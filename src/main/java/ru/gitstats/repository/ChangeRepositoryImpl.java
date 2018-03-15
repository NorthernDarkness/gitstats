package ru.gitstats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gitstats.model.Change;
import ru.gitstats.model.File;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class ChangeRepositoryImpl implements ChangeRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Change> save(Iterable<Change> entities) {
        for (Change change : entities) {
            final javax.persistence.Query query = entityManager.createNativeQuery("INSERT INTO changes (LINES_ADDED, LINES_DELETED, FILE_ID) VALUES (?0 ,?1, ?2 , " +
                    "(SELECT ID FROM files WHERE PATH = ?3))");
            query.setParameter(0, change.getLinesAdded());
            query.setParameter(1, change.getLinesDeleted());
            Set<File> files = change.getFiles();
            File file = files.stream().findFirst().get();
            query.setParameter(2, file.getPath());
            query.setParameter(3, change.getLinesAdded());
            query.executeUpdate();
        }
        List<Change> result = new ArrayList<>();
        entities.forEach(result::add);
        return result;
    }
}
