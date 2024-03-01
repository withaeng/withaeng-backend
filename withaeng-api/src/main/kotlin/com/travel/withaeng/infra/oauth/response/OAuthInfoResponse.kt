package com.travel.withaeng.infra.oauth.response

import com.travel.withaeng.domain.user.SocialType

interface OAuthInfoResponse {
    fun getEmail(): String?
    fun getNickname(): String
    fun getSocialType(): SocialType
    fun getProviderUniqueKey(): String
}