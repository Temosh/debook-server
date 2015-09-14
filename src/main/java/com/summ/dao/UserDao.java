package com.summ.dao;

import com.summ.entity.UserEntity;

/**
 * @author Serhii Tymoshenko
 */
public interface UserDao extends AbstractDao<UserEntity> {
    UserEntity findByLogin(String login);
}
