package com.mvote.dao.impl;

import com.mvote.dao.IUserDao;
import com.mvote.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements IUserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Users getUserByUserNameAndPassword(String username, String password) {

        String sql = "SELECT * FROM `users` WHERE `username` = ? AND `password` = ? AND `deleteFlag` = '0'";
        RowMapper<Users> rowMapper = new BeanPropertyRowMapper<>(Users.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, username, password);
    }

    @Override
    public boolean registerUser(String username, String password, String userImage, int age) {
        String sql = "INSERT INTO `users` ( `username`, `password`, `userImage`, `age`, `isAdmin`," +
                " `createdBy`, `createTs`, `modifiedBy`, `modifiedTs`, `deleteFlag`) " +
                "VALUES ('" + username + "', '" + password + "', '" + userImage + "', '" + age + "', '0', '1'," +
                " CURRENT_TIMESTAMP, '1', CURRENT_TIMESTAMP, '0')";
        return jdbcTemplate.update(sql) == 1;
    }
}
