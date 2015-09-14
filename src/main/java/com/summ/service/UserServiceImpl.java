package com.summ.service;

import com.summ.dao.UserDao;
import com.summ.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity getUser(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public List<UserEntity> getUsers() {
        return userDao.findAll();
    }
}
