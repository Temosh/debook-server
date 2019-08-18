package com.summ.debook.service;

import com.summ.debook.entity.UserEntity;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface UserService {
    UserEntity getUser(Long id);
    UserEntity getUserByLogin(String login);
}
