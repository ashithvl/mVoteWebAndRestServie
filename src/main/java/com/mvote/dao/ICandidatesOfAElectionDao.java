package com.mvote.dao;

import com.mvote.models.Candidate;
import com.mvote.models.Candidates;

import java.util.List;

public interface ICandidatesOfAElectionDao {

    List<Candidates> getElectionCandidatesList(int electionId);

    Candidate createCandidate(Candidate candidate);

}
