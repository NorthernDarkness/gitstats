package ru.gitstats;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.Edit;
import org.eclipse.jgit.diff.EditList;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevSort;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.revwalk.filter.RevFilter;
import org.eclipse.jgit.util.io.NullOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ru.gitstats.model.Change;
import ru.gitstats.model.Commit;
import ru.gitstats.model.User;
import ru.gitstats.repository.ChangeRepository;
import ru.gitstats.repository.CommitRepository;
import ru.gitstats.repository.UserRepository;
import ru.gitstats.services.ICommitService;

import java.io.IOException;
import java.util.*;

//import ru.gitstats.model.File;
//import ru.gitstats.repository.FileRepository;
//import ru.gitstats.repository.FileRepositoryCustom;

@Component
public class ApplicationStartup
        implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommitRepository commitRepository;

    @Autowired
    private ChangeRepository changeRepository;

    @Autowired
    private ICommitService commitService;
//
//    @Autowired
//    private FileRepositoryCustom fileRepository;

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        Git git = null;
        try {
//            git = Git.open(new java.io.File("F:\\Jproj\\testNg\\.git"));

            git = Git.open(new java.io.File("C:\\Users\\Scrin\\Desktop\\gitstats\\.git"));
//            git = Git.open(new java.io.File("D:\\JavaProj\\gitstats\\.git"));
        } catch (IOException e1) {
//             TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Repository repository = git.getRepository();
        git.branchList();
        RevWalk walk = new RevWalk(repository);
        try {
            walk.markStart(walk.parseCommit(repository.resolve(Constants.HEAD)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        walk.sort(RevSort.REVERSE);
        Multimap<User, Commit> userCommits =
                Multimaps.newListMultimap(new HashMap<>(), ArrayList::new);
        Set<Commit> commits = new HashSet();
        walk.setRevFilter(RevFilter.NO_MERGES);
        for (RevCommit commit : walk) {
            System.out.println(String.join(", ", commit.getAuthorIdent().getName(),
                    commit.getAuthorIdent().getEmailAddress(), String.valueOf(commit.getAuthorIdent().getWhen())));
            Set<Change> changes = fetchChanges(repository, commit);
            Commit commitEntity = prepareCommitEntity(userCommits, commit, changes);
            commits.add(commitEntity);
        }
        walk.close();
//        commitRepository.save(commits);
        userCommits.asMap().forEach((k, v) -> {
            k.setCommits(v);
        });

        userCommits.asMap().forEach((k, v) -> {
            v.forEach(c -> c.setUser(k));
        });
        userRepository.save(userCommits.keySet());





//        commitService.save(commits);
//        commitRepository.findAllCountGroupByEmail();
        return;
    }

    private Set<Change> fetchChanges(Repository repository, RevCommit commit) {
        DiffFormatter formatter = new DiffFormatter(NullOutputStream.INSTANCE);
        formatter.setRepository(repository);
        List<DiffEntry> diffEntryList = null;
        try {
            diffEntryList = formatter.scan(commit, commit.getParentCount() == 0 ? null : commit.getParent(0));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long linesDeleted = 0;
        long linesAdded = 0;
        String path = "";
        EditList editList = null;
        Set<Change> changes = new HashSet();
        for (DiffEntry diffEntry : diffEntryList) {
            try {
                editList = formatter.toFileHeader(diffEntry).toEditList();
                path = formatter.toFileHeader(diffEntry).getPath(DiffEntry.Side.NEW);
                if ("/dev/null".equals(path)) {
                    path = formatter.toFileHeader(diffEntry).getPath(DiffEntry.Side.OLD);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            for (Edit edit : editList) {
                linesDeleted += edit.getEndA() - edit.getBeginA();
                linesAdded += edit.getEndB() - edit.getBeginB();
            }
            Change change = new Change();
//            File file = new File();
//            file.setPath(path);
            change.setLinesAdded(linesAdded);
            change.setLinesDeleted(linesDeleted);
            change.setFile(path);
//            file.setChanges(changes);
//            fileRepository.save(file);
//            Set<File> files = new HashSet();
//            files.add(file);
//            change.setFiles(files);
            changes.add(change);
        }
        return changes;
    }

    private Commit prepareCommitEntity(Multimap<User, Commit> users, RevCommit commit, Set<Change> changes) {
        User user = new User();
        user.setName(commit.getAuthorIdent().getName());
        user.setEmail(commit.getAuthorIdent().getEmailAddress());

        Commit commitEntity = new Commit();
        HashSet<Commit> userCommits = new HashSet<>();
        user.setCommits(userCommits);
        commitEntity.setDate(commit.getAuthorIdent().getWhen());
        commitEntity.setMessage(commit.getName());
        commitEntity.setChanges(changes);
//        changeRepository.save(changes);
        changes.stream().forEach(c -> c.setCommit(commitEntity));
        users.put(user, commitEntity);
        return commitEntity;
    }

}