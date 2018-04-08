package com.summ.debook.service;

import com.summ.debook.dao.LocalDebtDao;
import com.summ.debook.entity.LocalDebtEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author Serhii Tymoshenko
 */
@Component
public class LocalDebtServiceImpl implements LocalDebtService {

    @Autowired
    private LocalDebtDao localDebtDao;

    @Transactional
    @Override
    public Set<LocalDebtEntity> getDebts(String login) {
        return localDebtDao.findByLogin(login);
    }
}
