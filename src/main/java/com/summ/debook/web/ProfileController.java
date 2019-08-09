package com.summ.debook.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Serhii Tymoshenko
 */
@RestController("/profile")
public class ProfileController {

    @PostMapping
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void updateProfile() {
    }

    @GetMapping
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void getProfile() {
    }
}
