package ru.gitstats.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gitstats.model.Commit;
import ru.gitstats.model.User;
import ru.gitstats.repository.UserRepository;

import java.util.*;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> loadAll() {

        return userRepository.findAll();
    }


    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

}
