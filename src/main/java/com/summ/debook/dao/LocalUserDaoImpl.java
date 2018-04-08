package com.summ.debook.dao;

import com.summ.debook.entity.LocalUserEntity;
import com.summ.debook.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@Repository
public class LocalUserDaoImpl extends AbstractDaoImpl<LocalUserEntity> implements LocalUserDao {

    public LocalUserDaoImpl() {
        super(LocalUserEntity.class);
    }

    @Override
    public List<LocalUserEntity> findByLogin(String login) {
        return null;
    }
}
