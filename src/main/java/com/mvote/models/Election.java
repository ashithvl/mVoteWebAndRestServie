package com.mvote.models;

public class Election {
    private int electionId;
    private String electionName;
    private String electionStartDate;
    private String electionEndDate;

    public int getElectionId() {
        return electionId;
    }

    public void setElectionId(int electionId) {
        this.electionId = electionId;
    }

    public String getElectionName() {
        return electionName;
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }

    public String getElectionStartDate() {
        return electionStartDate;
    }

    public void setElectionStartDate(String electionStartDate) {
        this.electionStartDate = electionStartDate;
    }

    public String getElectionEndDate() {
        return electionEndDate;
    }

    public void setElectionEndDate(String electionEndDate) {
        this.electionEndDate = electionEndDate;
    }
}
