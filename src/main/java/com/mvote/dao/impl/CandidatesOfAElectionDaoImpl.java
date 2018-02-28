package com.mvote.dao.impl;

import com.mvote.dao.ICandidatesOfAElectionDao;
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

    private List<Candidates> candidatesList = new ArrayList<>();

    @Override
    public List<Candidates> getElectionCandidatesList(int electionId) {

        String sql = "SELECT candidates.candidatesId,candidates.candidatesName,electioncandidates.electionId FROM `candidates` AS candidates INNER JOIN electioncandidates AS electioncandidates ON electioncandidates.candidatesId = candidates.candidatesId AND electioncandidates.electionId = ?";
        RowMapper<Candidates> rowMapper = new BeanPropertyRowMapper<>(Candidates.class);
        try {
            candidatesList = jdbcTemplate.query(sql, rowMapper, electionId);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }

        return candidatesList;
    }
}
