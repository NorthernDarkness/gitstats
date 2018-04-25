package ru.gitstats.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
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

    @RequestMapping(value = "get/{userId}", method = RequestMethod.GET)
    public ModelAndView getContactService(ModelAndView mav, @PathVariable long userId) {
        mav.addObject("model", userService.getUserViewModel(userId));
        mav.setViewName("pages/user");
        return mav;
    }




    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public ModelAndView getAll(ModelAndView mav) {
        mav.addObject("users", userService.loadAll());
        mav.setViewName("pages/users");
        return mav;
    }



}
