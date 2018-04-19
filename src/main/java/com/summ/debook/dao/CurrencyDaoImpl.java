package com.summ.debook.dao;

import com.summ.debook.entity.CurrencyEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Serhii Tymoshenko
 */
@Repository
public class CurrencyDaoImpl extends AbstractDaoImpl<CurrencyEntity> implements CurrencyDao {

    public CurrencyDaoImpl() {
        super(CurrencyEntity.class);
    }
}
