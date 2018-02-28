package com.mvote.service;

import com.mvote.models.CandidatesVote;

public interface ICandidateVoteService {

    CandidatesVote getCandidatesVote(int userId, int electionId, int candidateId);
}
