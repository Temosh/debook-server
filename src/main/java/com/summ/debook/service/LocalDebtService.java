package com.summ.debook.service;

import com.summ.debook.entity.DebtEntity;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface LocalDebtService {
    List<DebtEntity> getDebts(String login);
}
