package com.staxter

import com.staxter.userrepository.User

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
}
