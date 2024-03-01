package com.travel.withaeng.applicationservice

import com.travel.withaeng.domain.user.CreateUserDto
import com.travel.withaeng.domain.user.UserDto
import com.travel.withaeng.domain.user.UserService
import com.travel.withaeng.infra.oauth.params.OAuthLoginParams
import com.travel.withaeng.infra.oauth.response.OAuthInfoResponse
import com.travel.withaeng.infra.oauth.service.OAuthService
import com.travel.withaeng.security.authentication.UserInfo
import com.travel.withaeng.security.jwt.AuthToken
import com.travel.withaeng.security.jwt.AuthTokenGenerator
import org.springframework.stereotype.Service

@Service
class UserApplicationService(
    private val userService: UserService,
    private val oAuthService: OAuthService,
    private val authTokenGenerator: AuthTokenGenerator
) {

    fun login(params: OAuthLoginParams): AuthToken {
        val oauthInfoResponse = oAuthService.request(params)
        val userDto = findOrCreateUser(oauthInfoResponse)
        return authTokenGenerator.generate(UserInfo.from(userDto))
    }

    private fun findOrCreateUser(oAuthInfoResponse: OAuthInfoResponse): UserDto {
        return userService.findByProviderUniqueKeyOrNull(oAuthInfoResponse.getProviderUniqueKey())
            ?: userService.createUser(
                CreateUserDto(
                    nickname = oAuthInfoResponse.getNickname(),
                    socialType = oAuthInfoResponse.getSocialType(),
                    providerUniqueKey = oAuthInfoResponse.getProviderUniqueKey()
                )
            )
    }

}