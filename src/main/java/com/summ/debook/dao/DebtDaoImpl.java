package com.summ.debook.dao;

import com.summ.debook.entity.DebtEntity;
import com.summ.debook.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@Repository
public class DebtDaoImpl extends AbstractDaoImpl<DebtEntity> implements DebtDao {

    @Autowired
    private UserDao userDao;

    public DebtDaoImpl() {
        super(DebtEntity.class);
    }

    @Override
    public List<DebtEntity> findByLogin(String login) {
        return null;
    }
}
