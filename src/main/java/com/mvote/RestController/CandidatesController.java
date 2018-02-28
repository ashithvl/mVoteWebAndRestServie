package com.mvote.RestController;

import com.mvote.models.Candidates;
import com.mvote.models.CandidatesVote;
import com.mvote.models.Election;
import com.mvote.service.ICandidateVoteService;
import com.mvote.service.ICandidatesOfAElectionService;
import com.mvote.utils.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class CandidatesController {

    @Autowired
    ICandidatesOfAElectionService iCandidatesOfAElectionService;

    @Autowired
    ICandidateVoteService iCandidateVoteService;

    @RequestMapping(value = "/candidatesList/{electionId}")
    public ResponseEntity getCurrentElections(@PathVariable int electionId) {
        List<Candidates> candidatesList = iCandidatesOfAElectionService.getElectionCandidatesList(electionId);
        if (candidatesList.size() > 0)
            return new ResponseEntity<>(candidatesList, HttpStatus.OK);
        else
            return new ResponseEntity<>(new CustomErrorType("No Candidates found"), HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/getCandidateVote/{userId}/{electionId}/{candidateId}")
    public ResponseEntity getCandidateVote(@PathVariable int userId, @PathVariable int electionId,
                                           @PathVariable int candidateId) {
        CandidatesVote candidatesVote = iCandidateVoteService.getCandidatesVote(userId, electionId, candidateId);
        if (candidatesVote != null)
            return new ResponseEntity<>(candidatesVote, HttpStatus.OK);
        else
            return new ResponseEntity<>(new CustomErrorType("No votes found"), HttpStatus.NOT_FOUND);
    }


}
