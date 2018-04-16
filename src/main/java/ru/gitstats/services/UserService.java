package ru.gitstats.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gitstats.business.model.AverageCommitsPerMonthModel;
import ru.gitstats.business.model.UserViewModel;
import ru.gitstats.controllers.UserController;
import ru.gitstats.model.User;
import ru.gitstats.repository.CommitRepository;
import ru.gitstats.repository.UserRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommitRepository commitRepository;

    @Override
    public List<User> loadAll() {
        return userRepository.findAll();
    }

    public UserViewModel getUserViewModel(long id) {
        UserViewModel userViewModel = new UserViewModel();
        User user = userRepository.findOne(id);
        BigDecimal result = commitRepository.findAverageCommits(id);
        List<Object[]> userFileSatistic = userRepository.getUserFileStaticstic(id);
        for (Object[] r : userFileSatistic) {
            userViewModel.setMostChangableFile((String) r[0]);
            userViewModel.setNumberOfAddedLines(((BigDecimal) r[2]).longValue());
            userViewModel.setNumberOfRemovedLines(((BigDecimal) r[3]).longValue());
        }
        userViewModel.setAverageCommitNumberPerMonth(result.doubleValue());
        userViewModel.setName(user.getName());
        userViewModel.setEmail(user.getEmail());
        userViewModel.setCommitNumber(commitRepository.countByUser_Id(id));
        return userViewModel;
    }

    public User getUser(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

}
