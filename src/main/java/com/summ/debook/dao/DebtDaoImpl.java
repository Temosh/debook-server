package com.summ.debook.dao;

import com.summ.debook.entity.CurrencyEntity;
import com.summ.debook.entity.DebtEntity;
import com.summ.debook.entity.PersonEntity;
import org.springframework.stereotype.Repository;

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
        session = getSessionFactory().getCurrentSession();
        return session
                .createQuery(
                        "from DebtEntity d where d.person = :person and d.currency = :currency",
                        DebtEntity.class)
                .setParameter("person", personEntity)
                .setParameter("currency", currencyEntity)
                .uniqueResult();
    }
}
