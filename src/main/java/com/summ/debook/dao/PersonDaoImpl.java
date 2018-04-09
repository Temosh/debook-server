package com.summ.debook.dao;

import com.summ.debook.entity.PersonEntity;
import com.summ.debook.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@Repository
public class PersonDaoImpl extends AbstractDaoImpl<PersonEntity> implements PersonDao {

    public PersonDaoImpl() {
        super(PersonEntity.class);
    }

    @Override
    public List<PersonEntity> findByLogin(String login) {
        session = getSessionFactory().getCurrentSession();
        return session
                .createQuery(
                        "from PersonEntity p where p.ownerUser = :login",
                        PersonEntity.class)
                .setParameter("login", login)
                .list();
    }
}
