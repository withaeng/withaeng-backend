package com.travel.withaeng.domain.user

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(private val userRepository: UserRepository) {

    @Transactional
    fun createUser(createUserDto: CreateUserDto): UserDto {
        return userRepository.save(
            User.create(
                email = createUserDto.email,
                password = createUserDto.password,
                nickname = createUserDto.nickname,
                profileImageUrl = createUserDto.profileImageUrl,
                birth = createUserDto.birth,
                isMale = createUserDto.isMale,
                bio = createUserDto.bio
            )
        ).toDto()
    }
}