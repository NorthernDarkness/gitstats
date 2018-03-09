package ru.gitstats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.gitstats.model.Commit;
import ru.gitstats.model.User;

import java.util.List;

@Transactional
public interface CommitRepository extends JpaRepository<Commit, Long> {

    List<Commit> findByEmail();
}
