package com.summ.debook.service;

import com.summ.debook.entity.DebtEntity;
import com.summ.debook.entity.DebtRequestDataEntity;
import com.summ.debook.entity.UserEntity;

/**
 * @author Serhii Tymoshenko
 */
public interface DebtService {
    DebtEntity createDebt(DebtEntity debtEntity, long personId);
    DebtEntity updateDebt(DebtEntity partialDebtEntity, long personId, long currencyId);

    void applyDebt(UserEntity sourceUser, UserEntity targetUser, DebtRequestDataEntity debtRequestDataEntity);
    void applyDebtAdjustment(UserEntity sourceUser, UserEntity targetUser, DebtRequestDataEntity debtRequestDataEntity);
}
