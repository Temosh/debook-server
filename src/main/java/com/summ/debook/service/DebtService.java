package com.summ.debook.service;

import com.summ.debook.entity.DebtEntity;

/**
 * @author Serhii Tymoshenko
 */
public interface DebtService {
    DebtEntity createDebt(DebtEntity debtEntity, Long personId);
    DebtEntity updateDebt(DebtEntity partialDebtEntity, Long personId, Long currencyId);
}
