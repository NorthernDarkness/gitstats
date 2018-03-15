package ru.gitstats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.gitstats.model.Change;

import java.util.List;

public interface ChangeRepositoryCustom {

    List<Change> save(Iterable<Change> entities);
}
