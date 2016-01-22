package com.summ.debook.dao;

import com.summ.debook.entity.UserEntity;

/**
 * @author Serhii Tymoshenko
 */
public interface UserDao extends AbstractDao<UserEntity> {
    UserEntity findByLogin(String login);
}
