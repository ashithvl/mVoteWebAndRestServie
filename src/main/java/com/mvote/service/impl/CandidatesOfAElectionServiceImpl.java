package com.mvote.service.impl;

import com.mvote.dao.ICandidatesOfAElectionDao;
import com.mvote.models.Candidate;
import com.mvote.models.Candidates;
import com.mvote.service.ICandidatesOfAElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatesOfAElectionServiceImpl implements ICandidatesOfAElectionService {

    @Autowired
    ICandidatesOfAElectionDao iCandidatesOfAElectionDao;

    @Override
    public List<Candidates> getElectionCandidatesList(int electionId) {
        return iCandidatesOfAElectionDao.getElectionCandidatesList(electionId);
    }

    @Override
    public Candidate createCandidate(Candidate candidate) {
        return iCandidatesOfAElectionDao.createCandidate(candidate);
    }
}
