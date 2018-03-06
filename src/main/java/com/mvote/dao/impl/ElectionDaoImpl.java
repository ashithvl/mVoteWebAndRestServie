package com.mvote.dao.impl;

import com.mvote.dao.IElectionDao;
import com.mvote.models.AdharCard;
import com.mvote.models.Election;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ElectionDaoImpl implements IElectionDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private List<Election> electionArrayList = new ArrayList<>();

    @Override
    public List<Election> getCurrentElectionList() {

        String sql = "SELECT * FROM `election` WHERE electionStartDate <= CURRENT_TIMESTAMP AND electionEndDate >= CURRENT_TIMESTAMP";
        RowMapper<Election> rowMapper = new BeanPropertyRowMapper<>(Election.class);
        try {
            electionArrayList = jdbcTemplate.query(sql, rowMapper);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return electionArrayList;
    }

    @Override
    public List<Election> getPreviousElectionList() {

        String sql = "SELECT * FROM `election` WHERE electionEndDate <= CURRENT_TIMESTAMP";
        RowMapper<Election> rowMapper = new BeanPropertyRowMapper<>(Election.class);
        try {
            electionArrayList = jdbcTemplate.query(sql, rowMapper);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return electionArrayList;
    }

    @Override
    public List<Election> getCurrentAndUpComingElectionListForWeb() {

        String sql = "SELECT * FROM `election` WHERE (electionStartDate <= CURRENT_TIMESTAMP AND electionEndDate >= CURRENT_TIMESTAMP) OR (electionStartDate >= CURRENT_TIMESTAMP)";
        RowMapper<Election> rowMapper = new BeanPropertyRowMapper<>(Election.class);
        try {
            electionArrayList = jdbcTemplate.query(sql, rowMapper);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return electionArrayList;
    }

    @Override
    public List<Election> getPreviousElectionListForWeb() {
        String sql = "SELECT * FROM `election` WHERE electionEndDate <= CURRENT_TIMESTAMP";
        RowMapper<Election> rowMapper = new BeanPropertyRowMapper<>(Election.class);
        try {
            electionArrayList = jdbcTemplate.query(sql, rowMapper);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return electionArrayList;
    }

    @Override
    public Election createElection(Election election) {
        Election newElection = null;

        String sql = "INSERT INTO `election` (`electionName`, `electionStartDate`, `electionEndDate`, `createdBy`, `createdTs`, `modifiedBy`, `modifiedTs`, `deleteFlag`) VALUES ( '" + election.getElectionName() + "', '" + election.getElectionStartDate() + "', '" + election.getElectionEndDate() + "', '1', CURRENT_TIMESTAMP, '1', CURRENT_TIMESTAMP, '0')";
        int electionId;
        try {
            electionId = jdbcTemplate.update(sql);
            String selectQuery = "SELECT electionId,electionStartDate,electionEndDate FROM `election` WHERE electionId = ?";
            RowMapper<Election> rowMapper = new BeanPropertyRowMapper<>(Election.class);
            try {
                newElection = jdbcTemplate.queryForObject(selectQuery, rowMapper, electionId);
            } catch (DataAccessException e) {
                System.out.println(e.getMessage());
            }
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }

        return newElection;
    }

    @Override
    public void addCandidateToElection(String candidateId, String electionId) {

        String sql = "INSERT INTO `electioncandidates` ( `candidatesId`, `electionId`, `createdBy`, `createdTs`, `modifiedBy`, `modifiedTs`, `deleteFlag`) VALUES ('" + candidateId + "', '" + electionId + "', '1', CURRENT_TIMESTAMP, '1', CURRENT_TIMESTAMP, '0')";
        try {
            jdbcTemplate.update(sql);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }

    }
}
