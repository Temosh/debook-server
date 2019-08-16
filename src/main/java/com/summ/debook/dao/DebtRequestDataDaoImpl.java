package com.summ.debook.dao;

import com.summ.debook.entity.DebtRequestDataEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Serhii Tymoshenko
 */
@Repository
public class DebtRequestDataDaoImpl extends AbstractDaoImpl<DebtRequestDataEntity> implements DebtRequestDataDao {

    public DebtRequestDataDaoImpl() {
        super(DebtRequestDataEntity.class);
    }
}
