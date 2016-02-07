package com.summ.debook.service;

import com.summ.debook.dao.UserDao;
import com.summ.debook.dao.UserSecretDao;
import com.summ.debook.entity.AuthoritiesEntity;
import com.summ.debook.entity.UserEntity;
import com.summ.debook.entity.UserSecretEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Serhii Tymoshenko
 */
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserSecretDao userSecretDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.findByLogin(login);
        Set<AuthoritiesEntity> authoritiesEntity = userEntity.getAuthoritieses();

        Set<GrantedAuthority> authorities = new HashSet<>();
        authoritiesEntity.stream().forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority.getId().getAuthority())));

        User user = new User(userEntity.getLogin(), userEntity.getUserSecret().getHash(), authorities);

        return user;
    }

    @Override
    public void createNewUser(String login, String email, String name, String surname, CharSequence password) {
        UserEntity user = new UserEntity();
        user.setLogin(login);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);

        UserSecretEntity userSecret = new UserSecretEntity();
        userSecret.setUser(user);
        userSecret.setHash(passwordEncoder.encode(password));
        user.setUserSecret(userSecret);

        user.setActivated(true);

        //TODO CRITICAL!!! Should be in one transaction
        userDao.create(user);
        userSecretDao.create(userSecret);
    }
}
