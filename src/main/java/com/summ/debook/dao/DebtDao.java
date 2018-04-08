package com.summ.debook.dao;

import com.summ.debook.entity.DebtEntity;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface DebtDao extends AbstractDao<DebtEntity> {
    List<DebtEntity> findByLogin(String login);
}
