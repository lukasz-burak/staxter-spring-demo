package com.staxter.login.impl;

class WrongCredentialsException extends RuntimeException {
    WrongCredentialsException() {
        super("Username and password do not match");
    }
}
