package ru.gitstats.services;

import ru.gitstats.model.User;

import java.util.List;

public interface IUserService {
    List<User> loadAll();
}
