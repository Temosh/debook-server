package com.summ.debook.dao;

import com.summ.debook.entity.UserSecretEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Serhii Tymoshenko
 */
@Repository
public class UserSecretDaoImpl extends AbstractDaoImpl<UserSecretEntity> implements UserSecretDao {

    public UserSecretDaoImpl() {
        super(UserSecretEntity.class);
    }
}
