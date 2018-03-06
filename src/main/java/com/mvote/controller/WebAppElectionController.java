package com.mvote.controller;

import com.mvote.models.Candidate;
import com.mvote.models.Candidates;
import com.mvote.models.Election;
import com.mvote.models.Users;
import com.mvote.service.ICandidatesOfAElectionService;
import com.mvote.service.IElectionService;
import com.mvote.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @RequestMapping(value = "/election/createCandidate", method = RequestMethod.GET)
    public String createCandidate(Model model) {
        Candidate candidate = new Candidate();
        model.addAttribute("candidate", candidate);
        return "createCandidate";
    }

    @RequestMapping(value = "/election/createCandidate", method = RequestMethod.POST)
    public String createCandidate(@ModelAttribute("candidate") Candidate candidate) {
        iCandidatesOfAElectionService.createCandidate(candidate);
        return "redirect:/election";
    }

    @RequestMapping(value = "/election/createElection", method = RequestMethod.GET)
    public String createElection(Model model) {
        Election election = new Election();
        model.addAttribute("election", election);
        return "createElection";
    }

    @RequestMapping(value = "/election/createElection", method = RequestMethod.POST)
    public String createElection(@ModelAttribute("election") Election election) {
        System.out.println(election.getElectionEndDate());
        iElectionService.createElection(election);
        return "redirect:/election";
    }

    @RequestMapping(value = "/election/{id}/cur", method = RequestMethod.GET)
    public String electionCandidatesCur(@PathVariable("id") int id, Model model) {
        List<Candidates> candidatesList = iCandidatesOfAElectionService.getElectionCandidatesList(id);
        List<Candidates> candidatesNotInElectionList = iCandidatesOfAElectionService.getElectionCandidatesNotInList(id);
        model.addAttribute("candidatesList", candidatesList);
        model.addAttribute("candidatesNotInElectionList", candidatesNotInElectionList);
        model.addAttribute("electionId", id);
        return "electionCandidates";
    }

    @RequestMapping(value = "/election/{id}/cur/error", method = RequestMethod.GET)
    public String electionCandidatesCurError(@PathVariable("id") int id, Model model) {
        List<Candidates> candidatesList = iCandidatesOfAElectionService.getElectionCandidatesList(id);
        List<Candidates> candidatesNotInElectionList = iCandidatesOfAElectionService.getElectionCandidatesNotInList(id);
        model.addAttribute("candidatesList", candidatesList);
        model.addAttribute("candidatesNotInElectionList", candidatesNotInElectionList);
        model.addAttribute("error", "Choose an Option");
        model.addAttribute("electionId", id);
        return "electionCandidates";
    }

    @RequestMapping(value = "/election/{id}/pre", method = RequestMethod.GET)
    public String electionCandidatesPre(@PathVariable("id") int id, Model model) {
        List<Candidates> candidatesList = iCandidatesOfAElectionService.getElectionCandidatesList(id);
        model.addAttribute("candidatesList", candidatesList);
        model.addAttribute("pre", candidatesList);
        return "electionCandidates";
    }


    @RequestMapping(value = "/election/addCandidate", method = RequestMethod.POST)
    public String addCandidatesCurrentElection(@RequestParam("candidateId") String candidatesId,
                                               @RequestParam("electionId") String electionId, Model model) {
        if (candidatesId.equals("0")) {
            return "redirect:/election/" + Integer.parseInt(electionId) + "/cur/error";
        }
        iElectionService.addCandidateToElection(candidatesId,electionId);

        return "redirect:/election/" + Integer.parseInt(electionId) + "/cur";
    }

}
