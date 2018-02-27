package com.mvote.service.impl;

import com.mvote.dao.IUserDao;
import com.mvote.models.Users;
import com.mvote.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDao iUserDao;

    @Override
    public Users getUserByUserNameAndPassword(String username, String password) {
        return iUserDao.getUserByUserNameAndPassword(username, password);
    }

    @Override
    public Users registerUser(String cardNum, String password) {
        return iUserDao.registerUser(cardNum, password);
    }
}
