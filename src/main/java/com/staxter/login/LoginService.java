package com.staxter.login;

import com.staxter.userrepository.UserDto;
import com.staxter.userrepository.UserLoginDto;

public interface LoginService {
    UserDto loginUser(UserLoginDto userLogin);
}
