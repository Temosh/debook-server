package com.summ.debook.dao;

import com.summ.debook.entity.LocalDebtEntity;

import java.util.Set;

/**
 * @author Serhii Tymoshenko
 */
public interface LocalDebtDao extends AbstractDao<LocalDebtEntity> {
    Set<LocalDebtEntity> findByLogin(String login);
}
