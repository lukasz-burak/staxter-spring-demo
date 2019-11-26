package com.staxter.register.impl

import com.staxter.userrepository.UserAlreadyExistsException
import com.staxter.userrepository.UserDtoConverter
import com.staxter.userrepository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

import static com.staxter.TestData.getUser
import static com.staxter.TestData.getUserDto
import static com.staxter.TestData.getUserDtoWithPassword

class RegistrationServiceImplTest extends Specification {
    def userRepository = Mock(UserRepository)
    def passwordEncoder = Mock(PasswordEncoder)
    def userDtoConverter = Mock(UserDtoConverter)
    def tested = new RegistrationServiceImpl(userRepository, passwordEncoder, userDtoConverter)

    def "should register new user"() {
        given:
        def userToRegister = getUserDtoWithPassword()
        def user = getUser()
        passwordEncoder.encode(userToRegister.password) >> user.hashedPassword
        userRepository.createUser(_) >> user
        userDtoConverter.convertToDto(user) >> getUserDto()


        when:
        def result = tested.registerUser(userToRegister)

        then:
        with(result) {
            firstName == user.firstName
            lastName == user.lastName
            userName == user.userName
        }
    }

    def "should throw UserAlreadyExistsException"() {
        given:
        def userToRegister = getUserDtoWithPassword()
        def user = getUser()
        passwordEncoder.encode(userToRegister.password) >> user.hashedPassword
        userRepository.createUser(_) >> { throw new UserAlreadyExistsException() }

        when:
        tested.registerUser(userToRegister)

        then:
        thrown(UserAlreadyExistsException)
    }
}
