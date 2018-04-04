package com.summ.debook.service;

import com.summ.debook.dao.AuthoritiesDao;
import com.summ.debook.dao.UserDao;
import com.summ.debook.dao.UserSecretDao;
import com.summ.debook.entity.AuthoritiesEntity;
import com.summ.debook.entity.UserEntity;
import com.summ.debook.entity.UserSecretEntity;
import com.summ.debook.type.Authority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Serhii Tymoshenko
 */
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationServiceImpl.class.getName());

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserSecretDao userSecretDao;
    @Autowired
    private AuthoritiesDao authoritiesDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.findByLogin(login);
        Set<AuthoritiesEntity> authoritiesEntity = userEntity.getAuthorities();

        Set<GrantedAuthority> authorities = new HashSet<>();
        authoritiesEntity.stream().forEach(authority ->
                authorities.add(new SimpleGrantedAuthority(authority.getId().getAuthority().toString())));

        UserSecretEntity userSecretEntity = userSecretDao.findByUser(userEntity);

        return new User(userEntity.getLogin(), userSecretEntity.getHash(), authorities);
    }

    @Transactional
    @Override
    public void createNewUser(String login, String email, String name, String surname, CharSequence password) {
        UserEntity user = new UserEntity();
        user.setLogin(login);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setActivated(true);
        userDao.create(user);

        UserSecretEntity userSecret = new UserSecretEntity();
        userSecret.setUserId(user.getUserId());
        userSecret.setHash(passwordEncoder.encode(password));
        userSecretDao.create(userSecret);

        authoritiesDao.create(new AuthoritiesEntity(Authority.ROLE_USER, user));
    }
}
