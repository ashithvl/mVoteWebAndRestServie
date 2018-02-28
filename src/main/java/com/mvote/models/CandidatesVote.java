package com.mvote.models;

public class CandidatesVote {

    private int votesCount;
    private int electionId;
    private int candidateId;
    private String candidatesName;
    private String candidatesImage;
    private String vote;

    public int getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(int votesCount) {
        this.votesCount = votesCount;
    }

    public int getElectionId() {
        return electionId;
    }

    public void setElectionId(int electionId) {
        this.electionId = electionId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidatesName() {
        return candidatesName;
    }

    public void setCandidatesName(String candidatesName) {
        this.candidatesName = candidatesName;
    }

    public String getCandidatesImage() {
        return candidatesImage;
    }

    public void setCandidatesImage(String candidatesImage) {
        this.candidatesImage = candidatesImage;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}
