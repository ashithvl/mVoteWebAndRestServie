package com.mvote.models;

public class Candidate {

    private int candidatesId;
    private String  candidatesName;
    private String  candidatesImage;

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

    public String getCandidatesImage() {
        return candidatesImage;
    }

    public void setCandidatesImage(String candidatesImage) {
        this.candidatesImage = candidatesImage;
    }
}
