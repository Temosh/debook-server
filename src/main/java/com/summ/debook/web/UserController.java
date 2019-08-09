package com.summ.debook.web;

import com.summ.debook.entity.UserEntity;
import com.summ.debook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public UserEntity getUser(@PathVariable String userId) {
        return null;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public List<UserEntity> getUsers() {
        return null;
    }
}
