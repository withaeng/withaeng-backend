package com.travel.withaeng.security.authentication

import com.travel.withaeng.domain.user.UserDto
import com.travel.withaeng.domain.user.UserRole

data class UserInfo(
    val id: Long,
    val providerUniqueKey: String,
    val nickname: String,
    val roles: Set<UserRole>,
) {
    companion object {
        fun from(user: UserDto): UserInfo {
            return UserInfo(
                id = user.id,
                providerUniqueKey = user.providerUniqueKey,
                nickname = user.nickname,
                roles = user.roles
            )
        }
    }
}
