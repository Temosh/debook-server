package com.summ.debook.web;

import com.summ.debook.entity.UserEntity;
import com.summ.debook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Serhii Tymoshenko
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public UserEntity getUser() {
        return userService.getCurrentUser();
    }
}
