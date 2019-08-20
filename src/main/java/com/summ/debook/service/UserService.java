package com.summ.debook.service;

import com.summ.debook.entity.UserEntity;


/**
 * @author Serhii Tymoshenko
 */
public interface UserService {
    UserEntity getUser(Long id);
    UserEntity getUserByLogin(String login);
}
