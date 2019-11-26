package com.staxter.userrepository

import spock.lang.Specification

import static com.staxter.TestData.getUser

class UserDtoConverterTest extends Specification {
    def tested = new UserDtoConverter()

    def "should convert User to UserDto"() {
        given:
        def user = getUser()

        when:
        def result = tested.convertToDto(user)

        then:
        with(result) {
            id == user.id
            firstName == user.firstName
            lastName == user.lastName
            userName == user.userName
        }
    }
}
