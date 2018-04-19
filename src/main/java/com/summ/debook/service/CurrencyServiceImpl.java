package com.summ.debook.service;

import com.summ.debook.dao.CurrencyDao;
import com.summ.debook.entity.CurrencyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@Component
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyDao currencyDao;

    @Transactional
    @Override
    public List<CurrencyEntity> getCurrencies() {
        return currencyDao.findAll();
    }
}
