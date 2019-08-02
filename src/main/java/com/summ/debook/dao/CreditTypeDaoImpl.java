package com.summ.debook.dao;

import com.summ.debook.entity.CreditTypeEntity;
import com.summ.debook.type.CreditType;
import org.springframework.stereotype.Repository;

/**
 * @author Serhii Tymoshenko
 */
@Repository
public class CreditTypeDaoImpl extends AbstractDaoImpl<CreditTypeEntity> implements CreditTypeDao {
    public CreditTypeDaoImpl() {
        super(CreditTypeEntity.class);
    }

    @Override
    public CreditTypeEntity findByType(CreditType type) {
        return em.createQuery("from CreditTypeEntity c where c.type = :type", CreditTypeEntity.class)
                .setParameter("type", type)
                .getSingleResult();
    }
}
