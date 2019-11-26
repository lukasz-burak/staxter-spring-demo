package com.staxter.login.web;

import com.staxter.login.LoginService;
import com.staxter.login.WrongCredentialsException;
import com.staxter.userrepository.UserDto;
import com.staxter.userrepository.UserLoginDto;
import com.staxter.web.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/userservice/login")
class LoginController {

    private static final String WRONG_CREDENTIALS_ERROR_CODE = "WRONG_CREDENTIALS";
    private static final String WRONG_CREDENTIALS_ERROR_DESCRIPTION = "Username and password do not match";

    private final LoginService loginService;

    LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    UserDto registerUser(@RequestBody @Valid UserLoginDto user) {
        return loginService.loginUser(user);
    }

    @ExceptionHandler({WrongCredentialsException.class})
    ResponseEntity<ErrorResponse> handleWrongCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        new ErrorResponse(
                                WRONG_CREDENTIALS_ERROR_CODE,
                                WRONG_CREDENTIALS_ERROR_DESCRIPTION
                        )
                );
    }
}
