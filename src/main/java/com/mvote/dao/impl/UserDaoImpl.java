package com.mvote.dao.impl;

import com.mvote.dao.IUserDao;
import com.mvote.models.AdharCard;
import com.mvote.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
@Transactional
public class UserDaoImpl implements IUserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Users getUserByUserNameAndPassword(String username, String password) {
        String sql = "SELECT * FROM `users` WHERE `username` = ? AND `password` = ? AND `deleteFlag` = '0'";
        RowMapper<Users> rowMapper = new BeanPropertyRowMapper<>(Users.class);
        Users users = null;
        try {
            users = jdbcTemplate.queryForObject(sql, rowMapper, username, password);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public Users registerUser(String cardNum, String password) {
        String sql = "SELECT * FROM `aadharcard` WHERE cardnum = ?";
        RowMapper<AdharCard> rowMapper = new BeanPropertyRowMapper<>(AdharCard.class);
        Users users = null;
        AdharCard adharCard;
        try {
            adharCard = jdbcTemplate.queryForObject(sql, rowMapper, cardNum);
            if (adharCard.getAge() >= 18) {
                String insertSql = "INSERT INTO `users` ( `username`,`cardnum`, `password`, `userImage`, `age`, `isAdmin`," +
                        " `createdBy`, `createTs`, `modifiedBy`, `modifiedTs`, `deleteFlag`) " +
                        "VALUES (:username,:cardnum,:password,:userImage,:age,:isAdmin,:createdBy,:createTs,:modifiedBy," +
                        ":modifiedTs,:deleteFlag)";

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("username", adharCard.getName());
                parameters.put("cardnum", cardNum);
                parameters.put("password", password);
                parameters.put("userImage", adharCard.getUserImage());
                parameters.put("age", adharCard.getAge());
                parameters.put("isAdmin", 0);
                parameters.put("createdBy", 1);
                parameters.put("createTs", new Date());
                parameters.put("modifiedBy", 1);
                parameters.put("modifiedTs", new Date());
                parameters.put("deleteFlag", 0);

                if (namedParameterJdbcTemplate.update(insertSql, parameters) == 1) {
                    users = getUserByUserNameAndPassword(adharCard.getName(), password);
                }
            } else {
                return null;
            }
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
}
