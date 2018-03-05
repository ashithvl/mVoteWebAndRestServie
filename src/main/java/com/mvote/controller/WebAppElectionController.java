package com.mvote.controller;

import com.mvote.models.Candidate;
import com.mvote.models.Election;
import com.mvote.models.Users;
import com.mvote.service.ICandidatesOfAElectionService;
import com.mvote.service.IElectionService;
import com.mvote.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebAppElectionController {

    @Autowired
    IElectionService iElectionService;

    @Autowired
    ICandidatesOfAElectionService iCandidatesOfAElectionService;

    @RequestMapping("/election")
    public String showElection(Model model) {
        List<Election> currentAndUpcomingElectionList = iElectionService.getCurrentAndUpComingElectionListForWeb();
        List<Election> previousElectionList = iElectionService.getPreviousElectionListForWeb();

        model.addAttribute("current", currentAndUpcomingElectionList);
        model.addAttribute("previous", previousElectionList);

        return "election";
    }

    @RequestMapping("/election/createCandidate")
    public String createCandidate(Candidate candidate) {
        iCandidatesOfAElectionService.createCandidate(candidate);
        return "election";
    }

}
