package com.staxter.register.web;

import com.staxter.register.RegistrationService;
import com.staxter.userrepository.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/userservice/register")
class RegistrationController {

    private final RegistrationService registrationService;

    RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    UserDto registerUser(@RequestBody @Valid UserDto user) {
        return registrationService.registerUser(user);
    }
}
