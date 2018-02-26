package com.mvote.service;

import com.mvote.models.Users;

public interface IUserService {

    Users getUserByUserNameAndPassword(String username, String password);

    boolean registerUser(String username, String password, String userImage, int age);
}
