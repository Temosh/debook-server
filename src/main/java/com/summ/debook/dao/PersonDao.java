package com.summ.debook.dao;

import com.summ.debook.entity.PersonEntity;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface PersonDao extends AbstractDao<PersonEntity> {
    List<PersonEntity> findByLogin(String login);
}
