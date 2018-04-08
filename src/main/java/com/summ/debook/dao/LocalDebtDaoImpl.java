package com.summ.debook.dao;

import com.summ.debook.entity.LocalDebtEntity;
import com.summ.debook.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author Serhii Tymoshenko
 */
@Repository
public class LocalDebtDaoImpl extends AbstractDaoImpl<LocalDebtEntity> implements LocalDebtDao {

    @Autowired
    private UserDao userDao;

    public LocalDebtDaoImpl() {
        super(LocalDebtEntity.class);
    }

    @Override
    public Set<LocalDebtEntity> findByLogin(String login) {
        UserEntity user = userDao.findByLogin(login);
        return user.getLocalDebts();
    }
}
