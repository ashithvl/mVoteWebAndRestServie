package com.mvote.dao.impl;

import com.mvote.dao.ICandidateVoteDao;
import com.mvote.models.CandidatesVote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CandidatesVoteDaoImpl implements ICandidateVoteDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private CandidatesVote candidatesVote;

    @Override
    public CandidatesVote getCandidatesVote(int userId, int electionId, int candidateId) {

        String sql = "SELECT COUNT(votes.candidateId) as votesCount,votes.electionId,votes.candidateId,candidates.candidatesName,candidates.candidatesImage,IF(votes.userId = ?, 'true', 'false') AS vote FROM `votes`as votes INNER JOIN candidates as candidates on candidates.candidatesId = votes.candidateId WHERE votes.electionId = ? AND votes.candidateId = ? GROUP by candidateId";
        RowMapper<CandidatesVote> rowMapper = new BeanPropertyRowMapper<>(CandidatesVote.class);
        try {
            candidatesVote = jdbcTemplate.queryForObject(sql, rowMapper, userId, electionId, candidateId);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }

        return candidatesVote;
    }
}
