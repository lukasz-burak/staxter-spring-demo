package com.staxter.userrepository.impl;

import com.staxter.userrepository.User;
import com.staxter.userrepository.UserAlreadyExistsException;
import com.staxter.userrepository.UserDoesNotExistsException;
import com.staxter.userrepository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
class UserRepositoryImpl implements UserRepository {

    private final HashMap<String, User> users;

    UserRepositoryImpl() {
        users = new HashMap<>();
    }

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        if (users.containsKey(user.getUserName())) {
            throw new UserAlreadyExistsException();
        }
        users.put(user.getUserName(), user);
        return user;
    }

    @Override
    public User findUser(String username) throws UserDoesNotExistsException {
        return Optional.ofNullable(users.get(username))
                .orElseThrow(UserDoesNotExistsException::new);
    }
}
