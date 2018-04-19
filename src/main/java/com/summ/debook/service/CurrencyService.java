package com.summ.debook.service;

import com.summ.debook.entity.CurrencyEntity;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface CurrencyService {
    List<CurrencyEntity> getCurrencies();
}
