package com.summ.service;

import com.summ.dao.LocalDebtDao;
import com.summ.entity.LocalDebtEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@Component
public class LocalDebtServiceImpl implements LocalDebtService {

    @Autowired
    private LocalDebtDao localDebtDao;

    @Override
    public List<LocalDebtEntity> getDebts() {
        return localDebtDao.findAll();
    }
}
