package ru.gitstats.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gitstats.business.model.CommitsModel;
import ru.gitstats.business.model.ProjectViewModel;
import ru.gitstats.model.Commit;
//import ru.gitstats.model.File;
//import ru.gitstats.repository.ChangeRepositoryCustom;
import ru.gitstats.repository.ChangeRepository;
import ru.gitstats.repository.CommitRepository;
//import ru.gitstats.repository.FileRepositoryCustom;
import ru.gitstats.repository.UserRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommitService implements ICommitService {

    @Autowired
    private CommitRepository commitRepository;
//
//    @Autowired
//    private ChangeRepositoryCustom changeRepository;
//
//    @Autowired
//    private FileRepositoryCustom fileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChangeRepository changeRepository;

    public List<Object[]> getCommitsModel() {
        List<CommitsModel> models =  commitRepository.findAllCountGroupByEmail();
        List<Object[]> result = new ArrayList<>();
        for (CommitsModel model : models) {
            result.add(new Object[] {model.getEmail(), model.getCount(), model.getCount()});
        }
        return result;
    }

    public List<Object[]> getAverageCommitsModel() {
        List<Object[]> rowData = commitRepository.findAverageCountGroupByEmail();
        List<Object[]> result = new ArrayList<>();
        for (Object[] r : rowData) {
            result.add(new Object[] {r[0], r[1], r[1]});
        }
        return result;
    }

//    @Transactional
//    public void save(Iterable<Commit> commits) {
//        for (Commit commit : commits) {
//            final Set<Change> changes = commit.getChanges();
//            for (Change change : changes) {
//                for (File file : change.getFiles()) {
//                    fileRepository.save(file);
//                }
//                changeRepository.save(change);
//            }
//        }
//        commitRepository.save(commits);
//    }

    @Override
    public List<Commit> loadAll() {
        return null;
    }

    @Override
    public void addUser(Commit user) {

    }

    public ProjectViewModel getProjectModel() {
        ProjectViewModel projectViewModel = new ProjectViewModel();
        List<Object[]> rowData = commitRepository.findAverageCountGroupByEmail();
        for (Object[] r : rowData) {
            final Double res = r[1] == null ? 0.0 : ((BigDecimal) r[1]).doubleValue();
            projectViewModel.setName("project name");
            projectViewModel.setNumberOfFiles(changeRepository.getNumberOfUniqueFiles());
            projectViewModel.setNumberOfCommits(commitRepository.count());
            projectViewModel.setNumberOfUsers(userRepository.count());
            projectViewModel.setNumberOfAddedLines(changeRepository.getTitalNumberOfAddedLines());
            projectViewModel.setNumberOfRemovedLines(changeRepository.getTitalNumberOfDeletedLines());
            Object[] result = (Object[]) changeRepository.getMostFrequentlyChangedFile().get(0);
            String g = result.getClass().getName();
            projectViewModel.setMostChangableFile((String) result[0]);
            projectViewModel.setAverageCommitNumberPerMonth(commitRepository.getAverageNumberOfCommitsPerMonth());
        }
        return projectViewModel;
    }
}
