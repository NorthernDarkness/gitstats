package ru.gitstats.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.gitstats.model.Commit;
import ru.gitstats.services.CommitService;
import ru.gitstats.services.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/commits")
public class CommitController {

    private Logger logger = LoggerFactory.getLogger(CommitController.class);

    @Autowired
    private CommitService commitService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Commit> getContactService() {
        return commitService.getCommitNumber();
    }




}
