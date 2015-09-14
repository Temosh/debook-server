package com.summ.dao;

import com.summ.entity.UserEntity;

/**
 * @author Serhii Tymoshenko
 */
public class UserDaoImpl extends AbstractDaoImpl<UserEntity> implements UserDao {

    public UserDaoImpl() {
        super(UserEntity.class);
    }

    @Override
    public UserEntity findByLogin(String login) {
        session = getSessionFactory().openSession();
        tx = session.beginTransaction();
        UserEntity user = (UserEntity) session.createQuery(
                "from UserEntity u where u.login = :login")
                .setString("login", login)
                .uniqueResult();
        tx.commit();
        session.close();
        return user;
    }
}
