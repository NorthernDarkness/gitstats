package ru.gitstats.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.gitstats.model.Stats;
import ru.gitstats.model.User;
import ru.gitstats.repository.UserRepository;
import ru.gitstats.services.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String getContactService() {
//        git log --pretty="%ae" --shortstat --grep amartyn@tieto.mera.ru --no-merges

        return "123";
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("users", userService.loadAll());
        return "UserStats";
    }



}
