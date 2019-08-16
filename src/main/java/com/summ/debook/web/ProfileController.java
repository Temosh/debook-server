package com.summ.debook.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Serhii Tymoshenko
 */
@RestController
@RequestMapping("/profile")
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
