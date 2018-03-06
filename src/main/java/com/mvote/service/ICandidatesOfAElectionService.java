package com.mvote.service;

import com.mvote.models.Candidate;
import com.mvote.models.Candidates;

import java.util.List;

public interface ICandidatesOfAElectionService {

    List<Candidates> getElectionCandidatesList(int electionId);

    List<Candidates> getElectionCandidatesNotInList(int electionId);

    Candidate createCandidate(Candidate candidate);
}
