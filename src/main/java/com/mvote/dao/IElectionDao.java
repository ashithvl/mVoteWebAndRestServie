package com.mvote.dao;

import com.mvote.models.Election;

import java.util.List;

public interface IElectionDao {

    List<Election> getCurrentElectionList();

    List<Election> getPreviousElectionList();

}
