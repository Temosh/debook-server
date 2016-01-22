package com.summ.debook.dao;

import com.summ.debook.entity.LocalUserEntity;
import org.springframework.stereotype.Component;

/**
 * @author Serhii Tymoshenko
 */
@Component
public class LocalUserDaoImpl extends AbstractDaoImpl<LocalUserEntity> implements LocalUserDao {

    public LocalUserDaoImpl() {
        super(LocalUserEntity.class);
    }
}
