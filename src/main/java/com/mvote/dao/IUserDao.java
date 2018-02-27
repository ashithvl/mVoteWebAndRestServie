package com.mvote.dao;

import com.mvote.models.Users;

public interface IUserDao {

    Users getUserByUserNameAndPassword(String username, String password);

    Users registerUser(String cardNum, String password);

}
