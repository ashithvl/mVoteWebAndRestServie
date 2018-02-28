package com.mvote.service.impl;

import com.mvote.dao.ICandidateVoteDao;
import com.mvote.models.CandidatesVote;
import com.mvote.service.ICandidateVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateVoteServiceImpl implements ICandidateVoteService {

    @Autowired
    ICandidateVoteDao iCandidateVoteDao;

    @Override
    public CandidatesVote getCandidatesVote(int userId, int electionId, int candidateId) {
        return iCandidateVoteDao.getCandidatesVote(userId, electionId, candidateId);
    }
}
