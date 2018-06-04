package com.summ.debook.dao;

import com.summ.debook.entity.CurrencyEntity;
import com.summ.debook.entity.DebtEntity;
import com.summ.debook.entity.PersonEntity;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface DebtDao extends AbstractDao<DebtEntity> {
    DebtEntity find(PersonEntity personEntity, CurrencyEntity currencyEntity);
}
