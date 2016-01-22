package com.summ.debook.dao;

import com.summ.debook.entity.LocalDebtEntity;
import org.springframework.stereotype.Component;

/**
 * @author Serhii Tymoshenko
 */
@Component
public class LocalDebtDaoImpl extends AbstractDaoImpl<LocalDebtEntity> implements LocalDebtDao {

    public LocalDebtDaoImpl() {
        super(LocalDebtEntity.class);
    }
}
