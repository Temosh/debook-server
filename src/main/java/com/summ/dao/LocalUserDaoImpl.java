package com.summ.dao;

import com.summ.entity.LocalUserEntity;

/**
 * @author Serhii Tymoshenko
 */
public class LocalUserDaoImpl extends AbstractDaoImpl<LocalUserEntity> implements LocalUserDao {

    public LocalUserDaoImpl() {
        super(LocalUserEntity.class);
    }
}
