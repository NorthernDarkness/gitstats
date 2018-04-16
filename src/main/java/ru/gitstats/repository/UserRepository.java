package ru.gitstats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.gitstats.model.User;

import java.util.List;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT" +
            "  r.f," +
            "  max(co) m," +
            "  sum(sa) s1," +
            "  sum(sr) s2" +
            "FROM (SELECT" +
            "        c.file               f," +
            "        count(c.file)        co," +
            "        sum(c.lines_added)   sa," +
            "        sum(c.lines_deleted) sr" +
            "      FROM" +
            "        users u LEFT JOIN commits s ON u.id = s.user_id" +
            "        LEFT JOIN changes c ON s.id = c.commit_id" +
            "      GROUP BY c.file) r GROUP BY r.f ORDER BY m DESC ", nativeQuery = true)
    long getFileStatistic();

    @Query(value = "SELECT" +
            "  max(co) m" +
            "FROM (SELECT" +
            "        c.file               f," +
            "        count(c.file)        co," +
            "        sum(c.lines_added)   sa," +
            "        sum(c.lines_deleted) sr" +
            "      FROM" +
            "        users u LEFT JOIN commits s ON u.id = s.user_id AND user_id = ?1" +
            "        LEFT JOIN changes c ON s.id = c.commit_id" +
            "      GROUP BY c.file) r1", nativeQuery = true)
    long getMostChangableFileByUser(long id);

    @Query(value = "SELECT  r1.f,\n" +
            "  max(co) m,\n" +
            "  sum(sa),\n" +
            "  sum(sr)\n" +
            "FROM (SELECT\n" +
            "        c.file               f,\n" +
            "        count(c.file)        co,\n" +
            "        sum(c.lines_added)   sa,\n" +
            "        sum(c.lines_deleted) sr\n" +
            "      FROM\n" +
            "        users u LEFT JOIN commits s ON u.id = s.user_id AND user_id = 1\n" +
            "        LEFT JOIN changes c ON s.id = c.commit_id\n" +
            "      GROUP BY c.file) r1\n" +
            "WHERE r1.co = (SELECT max(r2.cu)\n" +
            "               FROM (SELECT count(c.file) cu\n" +
            "                     FROM\n" +
            "                       users u LEFT JOIN commits s ON u.id = s.user_id AND user_id = ?1\n" +
            "                       LEFT JOIN changes c ON s.id = c.commit_id\n" +
            "                     GROUP BY c.file) r2)\n", nativeQuery = true)
    List<Object[]> getUserFileStaticstic(long id);
}
