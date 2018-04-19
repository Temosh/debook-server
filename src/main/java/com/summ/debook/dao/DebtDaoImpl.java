package com.summ.debook.dao;

import com.summ.debook.entity.DebtEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@Repository
public class DebtDaoImpl extends AbstractDaoImpl<DebtEntity> implements DebtDao {

    public DebtDaoImpl() {
        super(DebtEntity.class);
    }

    @Override
    public List<DebtEntity> findByLogin(String login) {
        return null;
    }
}
