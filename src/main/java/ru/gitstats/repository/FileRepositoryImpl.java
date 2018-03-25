//package ru.gitstats.repository;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import ru.gitstats.model.File;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//
//@Transactional
//@Repository
//public class FileRepositoryImpl implements FileRepositoryCustom{
//
//    @PersistenceContext
//    EntityManager entityManager;
//
//    public File save(File file){
//        final Query query = entityManager.createNativeQuery("INSERT INTO files (PATH) " +
//                "    SELECT ?0 FROM dual" +
//                "        WHERE NOT EXISTS (SELECT * FROM files f" +
//                "                             WHERE f.PATH = ?0)");
//        query.setParameter(0, file.getPath());
//        query.executeUpdate();
//        return file;
//    }
//}
