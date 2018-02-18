package ru.gitstats.controllers;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.gitstats.model.Alias;
import ru.gitstats.repository.AliasRepository;
import ru.gitstats.services.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AliasRepository aliasRepository;


    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String getContactService() {
        return "123";
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public List<Alias> getAll() {
        List<Alias> result = aliasRepository.findAll();

        Git git = new Git(Git.cloneRepository().getRepository());
        LogCommand log = git.log();
        log.
        return result;

    }
}
