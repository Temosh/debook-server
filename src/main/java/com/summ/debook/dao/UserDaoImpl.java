package com.summ.debook.dao;

import com.summ.debook.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@Repository
public class UserDaoImpl extends AbstractDaoImpl<UserEntity> implements UserDao {

    public UserDaoImpl() {
        super(UserEntity.class);
    }

    @Override
    public UserEntity findByLogin(String login) {
        List<UserEntity> result = em.createQuery("from UserEntity u where u.login = :login", UserEntity.class)
                .setParameter("login", login)
                .getResultList();
        if (result.isEmpty()) return null;
        return result.get(0);
    }
}
