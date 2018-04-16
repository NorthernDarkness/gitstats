package ru.gitstats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.gitstats.business.model.CommitsModel;
import ru.gitstats.model.Commit;

import java.math.BigDecimal;
import java.util.List;

@Transactional
public interface CommitRepository extends JpaRepository<Commit, Long> {

    @Query(value = "select new ru.gitstats.business.model.CommitsModel(s.user.email, count(s)) from Commit s group by s.user.email ORDER BY count(s) DESC")
    List<CommitsModel> findAllCountGroupByEmail();

    @Query(value = "SELECT\n" +
            "  d.email,\n" +
            "  d.count / (12 * (YEAR(d.max) - YEAR(d.min)) + (MONTH(d.max) - MONTH(d.min)))\n" +
            "    AS r\n" +
            "FROM\n" +
            "  (SELECT\n" +
            "     u.email,\n" +
            "     min(DATE) AS min,\n" +
            "     max(DATE) AS max,\n" +
            "     count(*)  AS count\n" +
            "   FROM users u LEFT JOIN commits c ON u.id = c.user_id\n" +
            "   GROUP BY user_id) AS d\n" +
            "ORDER BY r DESC", nativeQuery = true)
    List<Object[]> findAverageCountGroupByEmail();

    @Query(value = "SELECT\n" +
            "  d.count / (12 * (YEAR(d.max) - YEAR(d.min)) + (MONTH(d.max) - MONTH(d.min)))\n" +
            "    AS r\n" +
            "FROM\n" +
            "  (SELECT\n" +
            "     min(DATE) AS min,\n" +
            "     max(DATE) AS max,\n" +
            "     count(*)  AS count\n" +
            "   FROM users u LEFT JOIN commits c ON u.id = c.user_id\n" +
            "   WHERE u.id = ?1) AS d\n" +
            "ORDER BY r DESC", nativeQuery = true)
    BigDecimal findAverageCommits(long id);


    @Query(value = "SELECT d.count / (12 * (YEAR(d.max) - YEAR(d.min)) + (MONTH(d.max) - MONTH(d.min))) avg\n" +
            "FROM (SELECT\n" +
            "        count(id)    count,\n" +
            "        min(c1.date) min,\n" +
            "        max(c1.date) max\n" +
            "      FROM commits c1\n" +
            "     ) d", nativeQuery = true)
    long getAverageNumberOfCommitsPerMonth();

    Long countByUser_Id(long id);
}