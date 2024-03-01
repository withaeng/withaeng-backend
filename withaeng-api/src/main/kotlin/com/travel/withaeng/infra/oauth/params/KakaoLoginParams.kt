package com.travel.withaeng.infra.oauth.params

import com.travel.withaeng.domain.user.SocialType
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

data class KakaoLoginParams(val authorizationCode: String) : OAuthLoginParams {

    override fun getSocialType(): SocialType {
        return SocialType.KAKAO
    }

    override fun makeBody(): MultiValueMap<String, String> {
        return LinkedMultiValueMap<String, String>().apply {
            add("code", authorizationCode)
        }
    }
}