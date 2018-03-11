package ru.gitstats;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.Edit;
import org.eclipse.jgit.diff.EditList;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.StopWalkException;
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
import ru.gitstats.model.File;
import ru.gitstats.model.User;
import ru.gitstats.repository.CommitRepository;
import ru.gitstats.repository.FileRepository;
import ru.gitstats.repository.UserRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ApplicationStartup
        implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommitRepository commitRepository;

    @Autowired
    private FileRepository fileRepository;

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        Git git = null;
        try {
            git = Git.open(new java.io.File("F:\\Jproj\\testNg\\.git"));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
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
        Set<User> users = new HashSet();
        Set<Commit> commits = new HashSet();
        walk.setRevFilter(RevFilter.NO_MERGES);
        for (RevCommit commit : walk) {
            System.out.println(String.join(", ", commit.getAuthorIdent().getName(),
                    commit.getAuthorIdent().getEmailAddress(), String.valueOf(commit.getAuthorIdent().getWhen())));
            Set<Change> changes = fetchChanges(repository, commit);
            dumpIntoDb(users, commits, commit, changes);
        }
        walk.close();
        userRepository.save(users);
        commitRepository.save(commits);
        commitRepository.findAllCountGroupByEmail();
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
        int linesDeleted = 0;
        int linesAdded = 0;
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
            change.setLinesAdded(linesAdded);
            change.setLinesDeleted(linesDeleted);
            File file = new File();
            file.setPath(path);
            fileRepository.save(file);
            Set<File> files = new HashSet();
            files.add(file);
            change.setFiles(files);
            changes.add(change);
        }
        return changes;
    }

    private void dumpIntoDb(Set<User> users, Set<Commit> commits, RevCommit commit, Set<Change> changes) {
        User user = new User();
        user.setName(commit.getAuthorIdent().getName());
        user.setEmail(commit.getAuthorIdent().getEmailAddress());
        users.add(user);
        Commit commitEntity = new Commit();
        commitEntity.setEmail(commit.getAuthorIdent().getEmailAddress());
        commitEntity.setDate(commit.getAuthorIdent().getWhen());
        commitEntity.setMessage(commit.getName());
        commitEntity.setChanges(changes);
        commits.add(commitEntity);
    }


    @AllArgsConstructor
    private static class MyFilter extends RevFilter {

        @NonNull
        String email;

        @Override
        public boolean include(RevWalk revWalk, RevCommit revCommit) throws StopWalkException, MissingObjectException, IncorrectObjectTypeException, IOException {
            return email.equals(revCommit.getAuthorIdent().getEmailAddress());
        }

        @Override
        public org.eclipse.jgit.revwalk.filter.RevFilter clone() {
            return null;
        }
    }
}