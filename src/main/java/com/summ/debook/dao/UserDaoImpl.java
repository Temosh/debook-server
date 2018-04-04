package com.summ.debook.dao;

import com.summ.debook.entity.UserEntity;
import org.springframework.stereotype.Repository;

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
        session = getSessionFactory().getCurrentSession();
        UserEntity user = (UserEntity) session.createQuery(
                "from UserEntity u where u.login = :login")
                .setParameter("login", login)
                .uniqueResult();
        return user;
    }
}
