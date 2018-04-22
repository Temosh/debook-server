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
        session = getSessionFactory().getCurrentSession();
        CreditTypeEntity creditType = (CreditTypeEntity) session.createQuery(
                "from CreditTypeEntity c where c.type = :type")
                .setParameter("type", type)
                .uniqueResult();
        return creditType;
    }
}
