package com.summ.debook.dao;

import com.summ.debook.entity.CurrencyEntity;
import com.summ.debook.entity.DebtEntity;
import com.summ.debook.entity.PersonEntity;
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
    public DebtEntity find(PersonEntity personEntity, CurrencyEntity currencyEntity) {
        List<DebtEntity> result = em.createQuery("from DebtEntity d where d.person = :person and d.currency = :currency", DebtEntity.class)
                .setParameter("person", personEntity)
                .setParameter("currency", currencyEntity)
                .getResultList();
        if (result.isEmpty()) {
            return null;
        } else if (result.size() > 1) {
            throw new IllegalStateException("Person has more than 1 debt entity with currency " + currencyEntity);
        } else {
            return result.get(0);
        }
    }
}
