package com.travel.withaeng.infra.oauth.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.travel.withaeng.domain.user.SocialType

@JsonIgnoreProperties(ignoreUnknown = true)
class NaverInfoResponse : OAuthInfoResponse {

    @JsonProperty("response")
    private lateinit var response: Response

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Response(val email: String?, val nickname: String)

    override fun getEmail(): String? {
        return response.email
    }

    override fun getNickname(): String {
        return response.nickname
    }

    override fun getSocialType(): SocialType {
        return SocialType.NAVER
    }

    override fun getProviderUniqueKey(): String {
        return "${getSocialType()}-${getEmail() ?: getNickname()}"
    }
}