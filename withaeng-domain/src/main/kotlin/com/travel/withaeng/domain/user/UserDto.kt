package com.travel.withaeng.domain.user

import java.time.LocalDate

data class UserDto(
    val id: Long,
    val email: String,
    val password: String,
    val nickname: String,
    val birth: LocalDate? = null,
    val isMale: Boolean? = null,
    val profileImageUrl: String? = null,
    val bio: String? = null,
    val roles: Set<UserRole>
)

fun User.toDto(): UserDto = UserDto(
    id = id,
    email = email,
    password = password,
    nickname = nickname,
    birth = birth,
    isMale = isMale,
    profileImageUrl = profileImageUrl,
    bio = bio,
    roles = roles
)

data class CreateUserDto(
    val email: String,
    val password: String,
    val nickname: String,
    val birth: LocalDate? = null,
    val isMale: Boolean? = null,
    val profileImageUrl: String? = null,
    val bio: String? = null,
)