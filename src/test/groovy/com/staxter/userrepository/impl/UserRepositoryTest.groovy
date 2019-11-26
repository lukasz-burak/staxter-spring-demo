package com.staxter.userrepository.impl

import com.staxter.userrepository.UserAlreadyExistsException
import com.staxter.userrepository.UserDoesNotExistsException
import spock.lang.Specification

import static com.staxter.TestData.getUser

class UserRepositoryTest extends Specification {
    def tested = new UserRepositoryImpl()

    def "should add new user to hashmap"() {
        given:
        def user = getUser()

        when:
        def result = tested.createUser(user)

        then:
        result == user
    }

    def "should throw exception as user with given username already exists"() {
        given:
        def user = getUser()
        tested.createUser(user)

        when:
        tested.createUser(user)

        then:
        thrown(UserAlreadyExistsException)
    }

    def "should return existing user"() {
        given:
        def user = getUser()
        tested.createUser(user)

        when:
        def result = tested.findUser(user.userName)

        then:
        result == user
    }

    def "should throw exception as user with given username does not exists"() {
        when:
        tested.findUser("not_existing_user")

        then:
        thrown(UserDoesNotExistsException)
    }
}
