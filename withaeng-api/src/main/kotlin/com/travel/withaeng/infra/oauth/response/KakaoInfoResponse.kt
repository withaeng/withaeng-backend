package com.travel.withaeng.infra.oauth.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.travel.withaeng.domain.user.SocialType

@JsonIgnoreProperties(ignoreUnknown = true)
class KakaoInfoResponse : OAuthInfoResponse {

    @JsonProperty("kakao_account")
    private lateinit var kakaoAccount: KakaoAccount

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class KakaoAccount(val profile: KakaoProfile, val email: String?)

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class KakaoProfile(val nickname: String)

    override fun getEmail(): String? {
        return kakaoAccount.email
    }

    override fun getNickname(): String {
        return kakaoAccount.profile.nickname
    }

    override fun getSocialType(): SocialType {
        return SocialType.KAKAO
    }

    override fun getProviderUniqueKey(): String {
        return "${getSocialType()}-${getEmail() ?: getNickname()}"
    }
}