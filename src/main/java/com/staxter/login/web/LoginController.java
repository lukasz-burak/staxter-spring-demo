package com.staxter.login.web;

import com.staxter.login.LoginService;
import com.staxter.userrepository.UserDto;
import com.staxter.userrepository.UserLoginDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/userservice/login")
class LoginController {

    private final LoginService loginService;

    LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    UserDto registerUser(@RequestBody @Valid UserLoginDto user) {
        return loginService.loginUser(user);
    }
}
