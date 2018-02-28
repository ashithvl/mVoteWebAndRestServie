package com.mvote.service;

import com.mvote.models.Election;

import java.util.List;

public interface IElectionService {

    List<Election> getCurrentElectionList();

    List<Election> getPreviousElectionList();

}
