//package ru.gitstats.repository;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import ru.gitstats.model.Change;
//import ru.gitstats.model.File;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.Set;
//
//@Repository
//@Transactional
//public class ChangeRepositoryImpl implements ChangeRepositoryCustom {
//
//    @PersistenceContext
//    EntityManager entityManager;
//
//    @Override
//    public Change save(Change change) {
//        final javax.persistence.Query query = entityManager.createNativeQuery(
//                "INSERT INTO changes (lines_added, lines_deleted, file_id) VALUES (?0 ,?1, " +
//                        "(SELECT id FROM files WHERE path = ?2))");
//        query.setParameter(0, change.getLinesAdded());
//        query.setParameter(1, change.getLinesDeleted());
//        Set<File> files = change.getFiles();
//        File file = files.stream().findFirst().get();
//        query.setParameter(2, file.getPath());
//        query.executeUpdate();
//        return change;
//    }
//}
