package com.summ.debook.security;

import com.summ.debook.entity.UserEntity;

/**
 * @author Serhii Tymoshenko
 */
public interface UserPrincipal {
    UserEntity getUserEntity();
}
