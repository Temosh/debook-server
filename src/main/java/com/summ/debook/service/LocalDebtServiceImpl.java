package com.summ.debook.service;

import com.summ.debook.dao.DebtDao;
import com.summ.debook.entity.DebtEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@Component
public class LocalDebtServiceImpl implements LocalDebtService {

    @Autowired
    private DebtDao debtDao;

    @Transactional
    @Override
    public List<DebtEntity> getDebts(String login) {
        return debtDao.findByLogin(login);
    }
}
