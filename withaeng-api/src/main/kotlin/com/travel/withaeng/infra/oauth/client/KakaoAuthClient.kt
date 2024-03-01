package com.travel.withaeng.infra.oauth.client

import com.travel.withaeng.common.Constants
import com.travel.withaeng.common.exception.WithaengException
import com.travel.withaeng.common.exception.WithaengExceptionType
import com.travel.withaeng.config.KakaoAuthProperty
import com.travel.withaeng.domain.user.SocialType
import com.travel.withaeng.infra.oauth.params.OAuthLoginParams
import com.travel.withaeng.infra.oauth.response.KakaoInfoResponse
import com.travel.withaeng.infra.oauth.response.OAuthInfoResponse
import com.travel.withaeng.infra.oauth.token.KakaoTokens
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate

@Component
class KakaoAuthClient(
    private val kakaoAuthProperty: KakaoAuthProperty,
    private val restTemplate: RestTemplate
) : OAuthClient {
    override fun getSocialType(): SocialType {
        return SocialType.KAKAO
    }

    override fun requestAccessToken(params: OAuthLoginParams): String {
        val url = kakaoAuthProperty.url.auth + AUTH_TOKEN_ENDPOINT
        val httpHeaders = HttpHeaders().apply { contentType = MediaType.APPLICATION_FORM_URLENCODED }
        val body = params.makeBody().apply {
            add(KEY_GRANT_TYPE, VALUE_GRANT_TYPE)
            add(KEY_CLIENT_ID, kakaoAuthProperty.clientId)
        }
        val request = HttpEntity(body, httpHeaders)
        val response = restTemplate.postForObject(url, request, KakaoTokens::class.java)
        return response?.accessToken ?: throw WithaengException.of(WithaengExceptionType.AUTHENTICATION_FAILURE)
    }

    override fun requestOAuthInfo(accessToken: String): OAuthInfoResponse {
        val url = kakaoAuthProperty.url.api + AUTH_INFO_ENDPOINT
        val httpHeaders = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
            set(KEY_AUTHORIZATION, "${Constants.Authentication.BEARER_TOKEN_PREFIX_WITH_WHITESPACE}$accessToken")
        }
        val body = LinkedMultiValueMap<String, String>().apply {
            add(KEY_PROPERTY_KEYS, VALUE_PROPERTY_KEYS)
        }
        val request = HttpEntity(body, httpHeaders)
        return restTemplate.postForObject(url, request, KakaoInfoResponse::class.java)
            ?: throw WithaengException.of(WithaengExceptionType.AUTHENTICATION_FAILURE)
    }

    companion object {
        private const val AUTH_TOKEN_ENDPOINT = "/oauth/token"
        private const val AUTH_INFO_ENDPOINT = "/v2/user/me"

        private const val KEY_GRANT_TYPE = "grant_type"
        private const val KEY_CLIENT_ID = "client_id"
        private const val KEY_AUTHORIZATION = "Authorization"
        private const val KEY_PROPERTY_KEYS = "property_keys"

        private const val VALUE_GRANT_TYPE = "authorization_code"
        private const val VALUE_PROPERTY_KEYS = "[\"kakao_account.email\", \"kakao_account.profile\"]"
    }
}