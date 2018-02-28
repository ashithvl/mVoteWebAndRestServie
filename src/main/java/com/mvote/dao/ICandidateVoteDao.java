package com.mvote.dao;

import com.mvote.models.CandidatesVote;

public interface ICandidateVoteDao {

    CandidatesVote getCandidatesVote(int userId,int electionId,int candidateId);

    String vote(int userId,int electionId,int candidateId);

}
