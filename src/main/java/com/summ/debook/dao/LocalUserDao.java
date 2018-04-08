package com.summ.debook.dao;

import com.summ.debook.entity.LocalUserEntity;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface LocalUserDao extends AbstractDao<LocalUserEntity> {
    List<LocalUserEntity> findByLogin(String login);
}
