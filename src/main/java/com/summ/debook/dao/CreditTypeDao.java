package com.summ.debook.dao;

import com.summ.debook.entity.CreditTypeEntity;
import com.summ.debook.type.CreditType;

/**
 * @author Serhii Tymoshenko
 */
public interface CreditTypeDao extends AbstractDao<CreditTypeEntity> {
    CreditTypeEntity findByType(CreditType type);
}
