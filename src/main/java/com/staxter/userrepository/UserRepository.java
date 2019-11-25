package com.staxter.userrepository;

public interface UserRepository {
    public User createUser(User user) throws UserAlreadyExistsException;
}
