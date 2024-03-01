package com.travel.withaeng.infra.oauth.params

import com.travel.withaeng.domain.user.SocialType
import org.springframework.util.MultiValueMap

interface OAuthLoginParams {
    fun getSocialType(): SocialType
    fun makeBody(): MultiValueMap<String, String>
}