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
    public List<PersonEntity> findByUser(UserEntity user) {
        return em.createQuery("from PersonEntity p where p.ownerUser = :user", PersonEntity.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<PersonEntity> findByConnection(UserEntity user1, UserEntity user2) {
        return em.createQuery("from PersonEntity p where" +
                "(p.ownerUser = :user1 and p.connectedUser = :user2) or" +
                "(p.ownerUser = :user2 and p.connectedUser = :user1)", PersonEntity.class)
                .setParameter("user1", user1)
                .setParameter("user2", user2)
                .getResultList();
    }

    @Override
    public PersonEntity findByConnectedUser(UserEntity ownerUser, UserEntity connectedUser) {
        List<PersonEntity> personList =  em.createQuery("from PersonEntity p where p.ownerUser = :ownerUser and p.connectedUser = :connectedUser", PersonEntity.class)
                .setParameter("ownerUser", ownerUser)
                .setParameter("connectedUser", connectedUser)
                .getResultList();
        if (personList.size() == 0) {
            return null;
        } else if (personList.size() == 1) {
            return personList.get(0);
        } else {
            throw new IllegalStateException("User " + ownerUser.getUserId() + " has more than 1 connection to user " + connectedUser.getUserId());
        }
    }
}
