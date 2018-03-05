package com.mvote.service.impl;

import com.mvote.dao.IElectionDao;
import com.mvote.models.Election;
import com.mvote.service.IElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectionServiceImpl implements IElectionService {

    @Autowired
    IElectionDao iElectionDao;

    @Override
    public List<Election> getCurrentElectionList() {
        return iElectionDao.getCurrentElectionList();
    }

    @Override
    public List<Election> getPreviousElectionList() {
        return iElectionDao.getPreviousElectionList();
    }

    @Override
    public List<Election> getCurrentAndUpComingElectionListForWeb() {
        return iElectionDao.getCurrentElectionList();
    }

    @Override
    public List<Election> getPreviousElectionListForWeb() {
        return iElectionDao.getPreviousElectionList();
    }

    @Override
    public Election createElection(Election election) {
        return iElectionDao.createElection(election);
    }
}
