package com.summ.dao;

import com.summ.entity.LocalDebtEntity;
import com.summ.entity.LocalUserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@Component
public class LocalDebtDaoImpl extends AbstractDaoImpl<LocalDebtEntity> implements LocalDebtDao {

    public LocalDebtDaoImpl() {
        super(LocalDebtEntity.class);
    }
}
