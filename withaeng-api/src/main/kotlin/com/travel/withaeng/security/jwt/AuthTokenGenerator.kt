package com.travel.withaeng.security.jwt

import com.travel.withaeng.common.Constants
import com.travel.withaeng.security.authentication.UserInfo
import org.springframework.stereotype.Component

@Component
class AuthTokenGenerator(private val jwtProvider: JwtProvider) {

    fun generate(userInfo: UserInfo): AuthToken {
        val accessToken = jwtProvider.createToken(userInfo, ACCESS_TOKEN_EXPIRE_TIME)
        val refreshToken = jwtProvider.createToken(userInfo, REFRESH_TOKEN_EXPIRE_TIME)

        return AuthToken(
            accessToken = accessToken,
            refreshToken = refreshToken,
            grantType = Constants.Authentication.BEARER_TYPE,
            expiresIn = ACCESS_TOKEN_EXPIRE_TIME / 1000L
        )
    }

    companion object {
        // TODO: Change expire times after developing application
        private const val ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 7L
        private const val REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 30L
    }
}