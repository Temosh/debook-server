package com.summ.debook.dao;

import com.summ.debook.entity.UserEntity;
import com.summ.debook.entity.UserSecretEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Serhii Tymoshenko
 */
@Repository
public class UserSecretDaoImpl extends AbstractDaoImpl<UserSecretEntity> implements UserSecretDao {

    public UserSecretDaoImpl() {
        super(UserSecretEntity.class);
    }

    @Override
    public UserSecretEntity findByUser(UserEntity user) {
        session = getSessionFactory().getCurrentSession();
        return (UserSecretEntity) session.createQuery(
                "from UserSecretEntity u where u.id = :user_id")
                .setInteger("user_id", user.getUserId())
                .uniqueResult();
    }
}
