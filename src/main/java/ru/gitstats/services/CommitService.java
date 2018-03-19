package ru.gitstats.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gitstats.business.model.AverageCommitsPerMonthModel;
import ru.gitstats.business.model.CommitsModel;
import ru.gitstats.model.Change;
import ru.gitstats.model.Commit;
import ru.gitstats.model.File;
import ru.gitstats.repository.ChangeRepositoryCustom;
import ru.gitstats.repository.CommitRepository;
import ru.gitstats.repository.FileRepositoryCustom;
import ru.gitstats.repository.UserRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CommitService implements ICommitService {

    @Autowired
    private CommitRepository commitRepository;

    @Autowired
    private ChangeRepositoryCustom changeRepository;

    @Autowired
    private FileRepositoryCustom fileRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CommitsModel> getCommitsModel() {
        return commitRepository.findAllCountGroupByEmail();
    }

    public List<AverageCommitsPerMonthModel> getAverageCommitsModel() {
        List<Object[]> rowData = commitRepository.findAverageCountGroupByEmail();
        List<AverageCommitsPerMonthModel> list = new ArrayList<>();
        for (Object[] r : rowData) {
            final Double res = r[1] == null ? 0.0 : ((BigDecimal) r[1]).doubleValue();
            AverageCommitsPerMonthModel averageCommitsPerMonthModel
                    = new AverageCommitsPerMonthModel((String) r[0], res);
            list.add(averageCommitsPerMonthModel);
        }
        return list;
    }

    @Transactional
    public void save(Iterable<Commit> commits) {
        for (Commit commit : commits) {
            final Set<Change> changes = commit.getChanges();
            for (Change change : changes) {
                for (File file : change.getFiles()) {
                    fileRepository.save(file);
                }
                changeRepository.save(change);
            }
        }
        commitRepository.save(commits);
    }

    @Override
    public List<Commit> loadAll() {
        return null;
    }

    @Override
    public void addUser(Commit user) {

    }
}
