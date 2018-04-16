package ru.gitstats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.gitstats.business.model.CommitsModel;
import ru.gitstats.model.Change;
import ru.gitstats.model.Commit;

import java.io.Serializable;
import java.util.List;

@Transactional
public interface ChangeRepository<T> extends JpaRepository<Change, Long> {

//    @Query(value = "INSERT INTO changes (LINES_ADDED, LINES_DELETED, COMMIT_ID, FILE_ID) VALUES (?0 ,?1, ?3 , " +
//            "(SELECT ID FROM files WHERE PATH = ?4));", nativeQuery = true)
//    void insertIfFileNotExist(long linesAdded,long linesDeleted, long commitId, String path);

    @Query(value = "SELECT count(*) FROM (SELECT DISTINCT file FROM changes) as r", nativeQuery = true)
    public long getNumberOfUniqueFiles();

    @Query(value = "select sum(lines_added) from changes", nativeQuery = true)
    public long getTitalNumberOfAddedLines();

    @Query(value = "select sum(lines_deleted) from changes", nativeQuery = true)
    public long getTitalNumberOfDeletedLines();

    @Query(value = "SELECT\n" +
            "  file,\n" +
            "  count(file) c\n" +
            "FROM changes\n" +
            "GROUP BY file\n" +
            "HAVING c = (SELECT max(c)\n" +
            "            FROM (SELECT count(file) c\n" +
            "                  FROM changes c\n" +
            "                  GROUP BY file) r)", nativeQuery = true)
    public List<Object[]> getMostFrequentlyChangedFile();
}
