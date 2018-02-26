package com.mvote.dao;

import com.mvote.models.Users;

public interface IUserDao {

    Users getUserByUserNameAndPassword(String username, String password);

    boolean registerUser(String username, String password, String userImage, int age);

}
