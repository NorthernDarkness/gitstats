package ru.gitstats.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gitstats.model.Commit;
import ru.gitstats.repository.CommitRepository;
import ru.gitstats.repository.UserRepository;

import java.util.List;

@Service
public class CommitService implements IUserService {

    @Autowired
    private CommitRepository commitRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Commit> getCommitNumber() {
        userRepository.findAll();
        return userRepository.findAll();
    }

}
