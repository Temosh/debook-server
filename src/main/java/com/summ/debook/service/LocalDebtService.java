package com.summ.debook.service;

import com.summ.debook.entity.LocalDebtEntity;

import java.util.Set;

/**
 * @author Serhii Tymoshenko
 */
public interface LocalDebtService {
    Set<LocalDebtEntity> getDebts(String login);
}
