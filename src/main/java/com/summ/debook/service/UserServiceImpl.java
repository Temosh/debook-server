package com.summ.debook.service;

import com.summ.debook.dao.UserDao;
import com.summ.debook.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public UserEntity getCurrentUser() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUser(login);
    }

    @Transactional
    @Override
    public UserEntity getUser(String login) {
        return userDao.findByLogin(login);
    }

    @Transactional
    @Override
    public List<UserEntity> getUsers() {
        return userDao.findAll();
    }
}
