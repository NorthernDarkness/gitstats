package ru.gitstats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.gitstats.business.model.AverageCommitsPerMonthModel;
import ru.gitstats.business.model.CommitsModel;
import ru.gitstats.model.Commit;

import java.util.List;

@Transactional
public interface CommitRepository extends JpaRepository<Commit, Long> {

    @Query(value = "select new ru.gitstats.business.model.CommitsModel(s.user.email, count(s)) from Commit s group by s.user.email ORDER BY count(s) DESC")
    List<CommitsModel> findAllCountGroupByEmail();

    @Query(value = "SELECT  EMAIL,  d.count / (12 * (YEAR(d.max) - YEAR(d.min)) + (MONTH(d.max) - MONTH(d.min)))  as r FROM (SELECT EMAIL, min(DATE) AS min, max(DATE) AS max, count(*) as count  FROM commits GROUP BY EMAIL) AS d ORDER BY  r DESC", nativeQuery = true)
    List<Object[]> findAverageCountGroupByEmail();

}
