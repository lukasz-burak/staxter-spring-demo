package com.staxter

import com.staxter.userrepository.User
import com.staxter.userrepository.UserDto
import com.staxter.userrepository.UserLoginDto

class TestData {
    static getUser() {
        def user = new User()
        user.id = "id"
        user.firstName = "John"
        user.lastName = "Travolta"
        user.userName = "john.travolta"
        user.plainTextPassword = "password"
        user.hashedPassword = "paSSw0rd"
        return user
    }

    static getUserDto() {
        def user = new UserDto()
        user.id = "id"
        user.firstName = "John"
        user.lastName = "Travolta"
        user.userName = "john.travolta"
        return user
    }

    static getUserDtoWithPassword() {
        def user = new UserDto()
        user.firstName = "John"
        user.lastName = "Travolta"
        user.userName = "john.travolta"
        user.password = "password"
        return user
    }

}
