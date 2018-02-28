package com.mvote.RestController;

import com.mvote.models.Election;
import com.mvote.models.Users;
import com.mvote.service.IElectionService;
import com.mvote.utils.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ElectionController {

    @Autowired
    IElectionService iElectionService;

    @RequestMapping("/getCurrentElections")
    public ResponseEntity getCurrentElections() {
        List<Election> electionList = iElectionService.getCurrentElectionList();
        if (electionList.size() > 0)
            return new ResponseEntity<>(electionList, HttpStatus.OK);
        else
            return new ResponseEntity<>(new CustomErrorType("No Elections found"), HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/getPreviousElections")
    public ResponseEntity getPreviousElections() {
        List<Election> electionList = iElectionService.getPreviousElectionList();
        if (electionList.size() > 0)
            return new ResponseEntity<>(electionList, HttpStatus.OK);
        else
            return new ResponseEntity<>(new CustomErrorType("No Elections found"), HttpStatus.NOT_FOUND);
    }

}
