package com.summ.debook.service;

import com.summ.debook.dao.UserDao;
import com.summ.debook.entity.UserEntity;
import com.summ.debook.security.UserPrincipal;
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

    @Override
    public UserEntity getCurrentUser() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userPrincipal.getUserEntity();
    }

    @Transactional
    @Override
    public UserEntity getUser(Long id) {
        return userDao.find(id);
    }

    @Transactional
    @Override
    public UserEntity getUserByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Transactional
    @Override
    public List<UserEntity> getUsers() {
        return userDao.findAll();
    }
}
