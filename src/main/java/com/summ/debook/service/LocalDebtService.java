package com.summ.debook.service;

import com.summ.debook.entity.LocalDebtEntity;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface LocalDebtService {
    List<LocalDebtEntity> getDebts();
}
