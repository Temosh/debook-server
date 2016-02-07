package com.summ.debook.service;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Serhii Tymoshenko
 */
public interface AuthenticationService extends UserDetailsService {
    void createNewUser(String login, String email, String name, String surname, CharSequence password);
}
