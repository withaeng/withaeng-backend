package com.travel.withaeng.infra.oauth.params

import com.travel.withaeng.domain.user.SocialType
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

data class NaverLoginParams(
    val authorizationCode: String,
    val state: String
) : OAuthLoginParams {
    override fun getSocialType(): SocialType {
        return SocialType.NAVER
    }

    override fun makeBody(): MultiValueMap<String, String> {
        println("code: $authorizationCode, state: $state")
        return LinkedMultiValueMap<String, String>().apply {
            add("code", authorizationCode)
            add("state", state)
        }
    }
}