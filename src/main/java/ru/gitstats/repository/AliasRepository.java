package ru.gitstats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.gitstats.model.Alias;
import ru.gitstats.model.User;

@Transactional
public interface AliasRepository extends JpaRepository<Alias, Long> {


}
