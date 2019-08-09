package com.summ.debook.web;

import com.summ.debook.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Serhii Tymoshenko
 */
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * @return CSRF token as cookie {@code XSRF-TOKEN}
     */
    @RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> preFlight() {
        return ResponseEntity.noContent().build();
    }

    //TODO Temporary
    @PostMapping(value = "/register")
    public ResponseEntity<Void> register(@RequestParam String login, @RequestParam String email,
                                      @RequestParam String firstname, @RequestParam String lastname,
                                      @RequestParam String password, UriComponentsBuilder uriComponentsBuilder) {
        authenticationService.createNewUser(login, email, firstname, lastname, password);

        UriComponents userUriComponents = uriComponentsBuilder.path("/user").build();
        return ResponseEntity.created(userUriComponents.toUri()).build();
    }
}
