package ru.gitstats;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.StopWalkException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevSort;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.revwalk.filter.RevFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ru.gitstats.model.Commit;
import ru.gitstats.model.User;
import ru.gitstats.repository.CommitRepository;
import ru.gitstats.repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class ApplicationStartup
        implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommitRepository commitRepository;

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        Git git = null;
        try {
            git = Git.open(new File("F:\\Jproj\\testNg\\.git"));
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
        walk.sort(RevSort.REVERSE); // chronological order
//        walk.setRevFilter(new MyFilter("septentrio2013@yandex.ru"));
//        Map<String, String> userEmails = new HashMap<>();
        Set<User> users = new HashSet();
        Set<Commit> commits = new HashSet();
        for (RevCommit commit : walk) {
//            userEmails.put(commit.getAuthorIdent().getEmailAddress(), commit.getAuthorIdent().getName());
            System.out.println(String.join(", ", commit.getAuthorIdent().getName(),
                    commit.getAuthorIdent().getEmailAddress(), String.valueOf(commit.getAuthorIdent().getWhen())));
            User user = new User();
            user.setName(commit.getAuthorIdent().getName());
            user.setEmail(commit.getAuthorIdent().getEmailAddress());
            users.add(user);
            Commit commitEntity = new Commit();
            commitEntity.setEmail(commit.getAuthorIdent().getEmailAddress());
            commitEntity.setDate(commit.getAuthorIdent().getWhen());
            commits.add(commitEntity);
        }
        walk.close();
        userRepository.save(users);
        commitRepository.save(commits);
        return;
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