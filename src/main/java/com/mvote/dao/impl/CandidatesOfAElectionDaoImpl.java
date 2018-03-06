package com.mvote.dao.impl;

import com.mvote.dao.ICandidatesOfAElectionDao;
import com.mvote.models.Candidate;
import com.mvote.models.Candidates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CandidatesOfAElectionDaoImpl implements ICandidatesOfAElectionDao {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<Candidates> getElectionCandidatesList(int electionId) {

        List<Candidates> candidatesList = new ArrayList<>();
        String sql = "SELECT candidates.candidatesId,candidates.candidatesName,electioncandidates.electionId FROM `candidates` AS candidates INNER JOIN electioncandidates AS electioncandidates ON electioncandidates.candidatesId = candidates.candidatesId AND electioncandidates.electionId = ?";
        RowMapper<Candidates> rowMapper = new BeanPropertyRowMapper<>(Candidates.class);
        try {
            candidatesList = jdbcTemplate.query(sql, rowMapper, electionId);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }

        return candidatesList;
    }

    @Override
    public List<Candidates> getElectionCandidatesNotInList(int electionId) {

        List<Candidates> candidatesList = new ArrayList<>();
        String sql = "SELECT candidatesId,candidatesName,candidatesImage FROM `candidates` WHERE candidatesId NOT IN (SELECT candidatesId FROM electioncandidates WHERE electionId = ? )";
        RowMapper<Candidates> rowMapper = new BeanPropertyRowMapper<>(Candidates.class);
        try {
            candidatesList = jdbcTemplate.query(sql, rowMapper, electionId);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }

        return candidatesList;
    }

    @Override
    public Candidate createCandidate(Candidate candidate) {
        Candidate newCandidate = null;

        String sql = "INSERT INTO `candidates` ( `candidatesName`, `candidatesImage`, `createdBy`, `createdTs`, `modifiedBy`, `modifiedTs`, `deleFlag`) VALUES ( '" + candidate.getCandidatesName() + "', '" + candidate.getCandidatesImage() + "', '1', CURRENT_TIMESTAMP, '1', CURRENT_TIMESTAMP, '0')";
        int candidateId;
        try {
            candidateId = jdbcTemplate.update(sql);
            String selectQuery = "SELECT candidatesId,candidatesName,candidatesImage FROM `candidates` WHERE candidatesId = ?";
            RowMapper<Candidate> rowMapper = new BeanPropertyRowMapper<>(Candidate.class);
            try {
                newCandidate = jdbcTemplate.queryForObject(selectQuery, rowMapper, candidateId);
            } catch (DataAccessException e) {
                System.out.println(e.getMessage());
            }
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }

        return newCandidate;
    }
}
