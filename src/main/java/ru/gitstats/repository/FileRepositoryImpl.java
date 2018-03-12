package ru.gitstats.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gitstats.model.File;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Transactional
@Repository
public class FileRepositoryImpl implements FileRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;

    public void saveIfNotExist(File file){
        Query query = entityManager.createNativeQuery("INSERT INTO files (PATH) " +
                "    SELECT ? FROM dual" +
                "        WHERE NOT EXISTS (SELECT * FROM files f" +
                "                             WHERE f.PATH = ?)");
        query.setParameter(1, file.getPath());
        query.executeUpdate();
    }
}
