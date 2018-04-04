package com.summ.debook.dao;

import com.summ.debook.entity.AuthoritiesEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Serhii Tymoshenko
 */
@Repository
public class AuthoritiesDaoImpl extends AbstractDaoImpl<AuthoritiesEntity> implements AuthoritiesDao {
    public AuthoritiesDaoImpl() {
        super(AuthoritiesEntity.class);
    }
}
