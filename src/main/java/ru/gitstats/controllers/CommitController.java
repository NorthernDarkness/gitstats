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
    public String getContactService(Model model) {
        model.addAttribute("averageCommitsModels", commitService.getAverageCommitsModel());
        model.addAttribute("avgCol1", "email");
        model.addAttribute("avgCol2", "average number of commits per month");
        model.addAttribute("commitsModels", commitService.getCommitsModel());
        model.addAttribute("column1", "email");
        model.addAttribute("column2", "commit number");
        return "Commits";
    }

    @RequestMapping(value = "api", method = RequestMethod.GET)
    public @ResponseBody
    List<CommitsModel> getCommitsByEmail() {
        return commitService.getCommitsModel();
    }

    @RequestMapping(value = "stat", method = RequestMethod.GET)
    public ModelAndView getStatistic(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("model", commitService.getProjectModel());
        mav.setViewName("/layout");
        return mav;
    }


}
