package com.staxter.login;

public class WrongCredentialsException extends RuntimeException {
    public WrongCredentialsException() {
        super("Username and password do not match");
    }
}
