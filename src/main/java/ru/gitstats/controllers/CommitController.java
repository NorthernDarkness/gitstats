package ru.gitstats.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.gitstats.business.model.CommitsModel;
import ru.gitstats.services.CommitService;

import java.util.List;

@Controller
@RequestMapping(value = "/commits")
public class CommitController {

    private Logger logger = LoggerFactory.getLogger(CommitController.class);

    @Autowired
    private CommitService commitService;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ModelAndView getContactService(ModelAndView mav) {
        mav.addObject("averageCommitsModels", commitService.getAverageCommitsModel());
        mav.addObject("avgCol1", "email");
        mav.addObject("avgCol2", "average number of commits per month");
        mav.addObject("commitsModels", commitService.getCommitsModel());
        mav.addObject("column1", "email");
        mav.addObject("column2", "commit number");
        mav.setViewName("pages/commits");
        return mav;
    }

//    @RequestMapping(value = "api", method = RequestMethod.GET)
//    public @ResponseBody
//    List<CommitsModel> getCommitsByEmail() {
//        return commitService.getCommitsModel();
//    }

    @RequestMapping(value = "stat", method = RequestMethod.GET)
    public ModelAndView getStatistic(ModelAndView mav) {
        mav.addObject("model", commitService.getProjectModel());
        mav.setViewName("pages/projects");
        return mav;
    }


}
