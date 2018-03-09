package ru.gitstats.services;

import ru.gitstats.model.Commit;
import ru.gitstats.model.User;

import java.util.List;

public interface IUserService {
    List<Commit> loadAll();

    void addCommit(Commit commit);
}
