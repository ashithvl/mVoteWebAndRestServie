package com.mvote.models;

public class Candidates {

    private int electionId;
    private int candidatesId;
    private String  candidatesName;

    public int getElectionId() {
        return electionId;
    }

    public void setElectionId(int electionId) {
        this.electionId = electionId;
    }

    public int getCandidatesId() {
        return candidatesId;
    }

    public void setCandidatesId(int candidatesId) {
        this.candidatesId = candidatesId;
    }

    public String getCandidatesName() {
        return candidatesName;
    }

    public void setCandidatesName(String candidatesName) {
        this.candidatesName = candidatesName;
    }
}
