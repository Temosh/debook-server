package com.summ.debook.dao;

import com.summ.debook.entity.LocalDebtEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Serhii Tymoshenko
 */
@Repository
public class LocalDebtDaoImpl extends AbstractDaoImpl<LocalDebtEntity> implements LocalDebtDao {

    public LocalDebtDaoImpl() {
        super(LocalDebtEntity.class);
    }
}
