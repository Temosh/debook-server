package com.summ.debook.web;

import com.summ.debook.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Serhii Tymoshenko
 */
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void preFlight() {
        //Returns CSRF token for login/registration
    }

    //TODO Temporary
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestParam String login, @RequestParam String email, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String password) {
        authenticationService.createNewUser(login, email, firstname, lastname, password);
    }
}
