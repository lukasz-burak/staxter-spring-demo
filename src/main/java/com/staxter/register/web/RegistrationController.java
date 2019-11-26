package com.staxter.register.web;

import com.staxter.register.RegistrationService;
import com.staxter.userrepository.UserAlreadyExistsException;
import com.staxter.userrepository.UserDto;
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
@RequestMapping("/userservice/register")
class RegistrationController {

    private static final String USER_ALREADY_EXISTS_ERROR_CODE = "USER_ALREADY_EXISTS";
    private static final String USER_ALREADY_EXISTS_ERROR_DESCRIPTION = "A user with the given username already exists";

    private final RegistrationService registrationService;

    RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    UserDto registerUser(@RequestBody @Valid UserDto user) {
        return registrationService.registerUser(user);
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    ResponseEntity<ErrorResponse> handleUserAlreadyExistsException() {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponse(
                                USER_ALREADY_EXISTS_ERROR_CODE,
                                USER_ALREADY_EXISTS_ERROR_DESCRIPTION
                        )
                );
    }
}
