package ru.gitstats.services;

import ru.gitstats.model.Commit;
import ru.gitstats.model.User;

import java.util.List;

public interface ICommitService {
    List<Commit> loadAll();
    void addUser(Commit user);
//    void save(Iterable<Commit> commits);
}
