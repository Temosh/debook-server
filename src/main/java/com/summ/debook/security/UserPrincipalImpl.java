package com.summ.debook.security;

import com.summ.debook.entity.UserEntity;
import com.summ.debook.entity.UserSecretEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author Serhii Tymoshenko
 */
public class UserPrincipalImpl extends User implements UserPrincipal {

    private UserEntity userEntity;

    public UserPrincipalImpl(UserEntity userEntity, UserSecretEntity userSecretEntity, Collection<? extends GrantedAuthority> authorities) {
        super(userEntity.getLogin(), userSecretEntity.getHash(), authorities);
        this.userEntity = userEntity;
    }

    @Override
    public UserEntity getUserEntity() {
        return userEntity;
    }
}
