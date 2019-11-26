package com.staxter.register;

import com.staxter.userrepository.UserDto;

public interface RegistrationService {
    UserDto registerUser(UserDto user);
}
