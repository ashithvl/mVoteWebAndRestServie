package com.mvote.dao;

import com.mvote.models.Election;

import java.util.List;

public interface IElectionDao {

    List<Election> getCurrentElectionList();

    List<Election> getPreviousElectionList();

    List<Election> getCurrentAndUpComingElectionListForWeb();

    List<Election> getPreviousElectionListForWeb();

    Election createElection(Election election);
}
