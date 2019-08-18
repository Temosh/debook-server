package com.summ.debook.web;

import com.summ.debook.dto.User;
import com.summ.debook.entity.UserEntity;
import com.summ.debook.service.UserService;
import com.summ.debook.service.converter.IdConversionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        UserEntity userEntity = userService.getUser(IdConversionHelper.parseId(userId, "Wrong user ID format"));
        if (userEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id '" + userId + "' not found");
        }
        return convertUserEntityToDto(userEntity);
    }

    @GetMapping
    public List<User> getUsers(@RequestParam String login) {
        if (login == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User login parameter is required");
        }
        return Collections.singletonList(getUserByLogin(login));
    }

    private User getUserByLogin(String login) {
        UserEntity userEntity = userService.getUserByLogin(login);
        if (userEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with login '" + login + "' not found");
        }
        return convertUserEntityToDto(userEntity);
    }

    private User convertUserEntityToDto(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getUserId().toString());
        user.setName(userEntity.getLogin());
        user.setAvatarUrl(null);
        return user;
    }
}
