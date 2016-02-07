package com.summ.debook.dao;

import com.summ.debook.entity.UserSecretEntity;
import org.springframework.stereotype.Component;

/**
 * @author Serhii Tymoshenko
 */
@Component
public class UserSecretDaoImpl extends AbstractDaoImpl<UserSecretEntity> implements UserSecretDao {

    public UserSecretDaoImpl() {
        super(UserSecretEntity.class);
    }
}
