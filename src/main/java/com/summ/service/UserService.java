package com.summ.service;

import com.summ.entity.UserEntity;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface UserService {
    UserEntity getUser(String login);
    List<UserEntity> getUsers();
}
