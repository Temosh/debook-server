package com.summ.debook.dao;

import com.summ.debook.entity.UserEntity;
import com.summ.debook.entity.UserSecretEntity;

/**
 * @author Serhii Tymoshenko
 */
public interface UserSecretDao extends AbstractDao<UserSecretEntity> {
    UserSecretEntity findByUser(UserEntity user);
}
