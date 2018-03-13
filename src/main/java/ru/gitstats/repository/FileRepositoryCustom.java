package ru.gitstats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.gitstats.model.File;

public interface FileRepositoryCustom {

    //    void saveIfNotExist(File path);
    File save(File file);
}

