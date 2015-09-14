package com.summ.dao;

import com.summ.entity.LocalDebtEntity;
import com.summ.entity.LocalUserEntity;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public class LocalDebtDaoImpl extends AbstractDaoImpl<LocalDebtEntity> implements LocalDebtDao {

    public LocalDebtDaoImpl() {
        super(LocalDebtEntity.class);
    }
}
