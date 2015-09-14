package com.summ.web;

import com.summ.entity.UserEntity;
import com.summ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    //TODO Temporary
    @RequestMapping(method = RequestMethod.GET)
    public UserEntity login(@RequestParam String login) {
        return userService.getUser(login);
    }
}
