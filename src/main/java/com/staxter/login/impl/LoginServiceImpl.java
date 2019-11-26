package com.staxter.login.impl;

import com.staxter.login.LoginService;
import com.staxter.login.WrongCredentialsException;
import com.staxter.userrepository.UserDtoConverter;
import com.staxter.userrepository.UserLoginDto;
import com.staxter.userrepository.UserDto;
import com.staxter.userrepository.User;
import com.staxter.userrepository.UserDoesNotExistsException;
import com.staxter.userrepository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDtoConverter userDtoConverter;

    LoginServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDtoConverter = userDtoConverter;
    }

    @Override
    public UserDto loginUser(UserLoginDto userLogin) {
        try {
            User user = userRepository.findUser(userLogin.getUserName());
            if (passwordEncoder.matches(userLogin.getPassword(), user.getHashedPassword())) {
                return userDtoConverter.convertToDto(user);
            }
        } catch (UserDoesNotExistsException e) {
            throw new WrongCredentialsException();
        }
        throw new WrongCredentialsException();
    }
}
