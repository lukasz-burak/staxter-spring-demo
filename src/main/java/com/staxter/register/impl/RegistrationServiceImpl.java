package com.staxter.register.impl;

import com.staxter.register.RegistrationService;
import com.staxter.userrepository.UserDto;
import com.staxter.userrepository.User;
import com.staxter.userrepository.UserDtoConverter;
import com.staxter.userrepository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDtoConverter userDtoConverter;

    RegistrationServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDtoConverter = userDtoConverter;
    }

    @Override
    public UserDto registerUser(UserDto user) {
        return userDtoConverter.convertToDto(userRepository.createUser(buildUser(user)));
    }

    private User buildUser(UserDto userDto) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        // I don't really see the point of storing plaintext password, it's not secure.
        // I've placed it here only as it's strong requirement, in a real app I would not store this information.
        user.setPlainTextPassword(userDto.getPassword());
        user.setHashedPassword(passwordEncoder.encode(userDto.getPassword()));

        return user;
    }
}
