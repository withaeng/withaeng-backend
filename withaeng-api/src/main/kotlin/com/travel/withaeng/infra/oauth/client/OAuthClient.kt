package com.travel.withaeng.infra.oauth.client

import com.travel.withaeng.domain.user.SocialType
import com.travel.withaeng.infra.oauth.params.OAuthLoginParams
import com.travel.withaeng.infra.oauth.response.OAuthInfoResponse

interface OAuthClient {
    fun getSocialType(): SocialType
    fun requestAccessToken(params: OAuthLoginParams): String
    fun requestOAuthInfo(accessToken: String): OAuthInfoResponse
}