package ru.gitstats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.gitstats.model.File;

@Transactional
public interface FileRepository extends JpaRepository<File, Long> {

    @Query(name = "INSERT INTO files (PATH) " +
            "    SELECT ?0 FROM dual" +
            "        WHERE NOT EXISTS (SELECT * FROM files f" +
            "                             WHERE f.PATH = ?0)")
    @Override
    File save(File path);
}
