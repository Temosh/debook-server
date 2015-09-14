package com.summ.service;

import com.summ.entity.LocalDebtEntity;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface LocalDebtService {
    List<LocalDebtEntity> getDebts();
}
