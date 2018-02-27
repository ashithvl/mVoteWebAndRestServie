package com.mvote.service;

import com.mvote.models.Users;

public interface IUserService {

    Users getUserByUserNameAndPassword(String username, String password);

    Users registerUser(String cardNum, String password);
}
