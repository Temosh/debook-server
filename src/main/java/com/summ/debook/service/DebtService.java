package com.summ.debook.service;

import com.summ.debook.entity.DebtEntity;

/**
 * @author Serhii Tymoshenko
 */
public interface DebtService {
    DebtEntity createDebt(DebtEntity debtEntity, long personId);
    DebtEntity updateDebt(DebtEntity partialDebtEntity, long personId, long currencyId);
}
