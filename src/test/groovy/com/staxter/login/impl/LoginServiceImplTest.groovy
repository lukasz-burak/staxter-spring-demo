package com.staxter.login.impl

import com.staxter.userrepository.UserDoesNotExistsException
import com.staxter.userrepository.UserDtoConverter
import com.staxter.userrepository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

import static com.staxter.TestData.getUser
import static com.staxter.TestData.getUserDto
import static com.staxter.TestData.getUserLoginDto

class LoginServiceImplTest extends Specification {
    def userRepository = Mock(UserRepository)
    def passwordEncoder = Mock(PasswordEncoder)
    def userDtoConverter = Mock(UserDtoConverter)
    def tested = new LoginServiceImpl(userRepository, passwordEncoder, userDtoConverter)


    def "should authenticate user and return user details"() {
        given:
        def userLogin = getUserLoginDto()
        def user = getUser()
        def userDto = getUserDto()
        userRepository.findUser(userLogin.userName) >> user
        passwordEncoder.matches(userLogin.password, user.hashedPassword) >> true
        userDtoConverter.convertToDto(user) >> userDto

        when:
        def result = tested.loginUser(userLogin)

        then:
        result == userDto
    }

    def "should not match user password and throw WrongCredentialsException"() {
        given:
        def userLogin = getUserLoginDto()
        def user = getUser()
        userRepository.findUser(userLogin.userName) >> user
        passwordEncoder.matches(userLogin.password, user.hashedPassword) >> false

        when:
        tested.loginUser(userLogin)

        then:
        thrown(WrongCredentialsException)
    }

    def "should not find user and throw WrongCredentialsException"() {
        given:
        def userLogin = getUserLoginDto()
        userRepository.findUser(userLogin.userName) >> { throw new UserDoesNotExistsException() }

        when:
        tested.loginUser(userLogin)

        then:
        thrown(WrongCredentialsException)
    }
}
