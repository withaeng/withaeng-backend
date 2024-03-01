package com.travel.withaeng.infra.oauth.client

import com.travel.withaeng.common.Constants
import com.travel.withaeng.common.exception.WithaengException
import com.travel.withaeng.common.exception.WithaengExceptionType
import com.travel.withaeng.config.NaverAuthProperty
import com.travel.withaeng.domain.user.SocialType
import com.travel.withaeng.infra.oauth.params.OAuthLoginParams
import com.travel.withaeng.infra.oauth.response.NaverInfoResponse
import com.travel.withaeng.infra.oauth.response.OAuthInfoResponse
import com.travel.withaeng.infra.oauth.token.NaverTokens
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate

@Component
class NaverAuthClient(
    private val naverAuthProperty: NaverAuthProperty,
    private val restTemplate: RestTemplate
) : OAuthClient {
    override fun getSocialType(): SocialType {
        return SocialType.NAVER
    }

    override fun requestAccessToken(params: OAuthLoginParams): String {
        val url = naverAuthProperty.url.auth + AUTH_TOKEN_ENDPOINT
        val body = params.makeBody()
        val httpHeaders = HttpHeaders(body).apply {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
            add(KEY_GRANT_TYPE, VALUE_GRANT_TYPE)
            add(KEY_CLIENT_ID, naverAuthProperty.clientId)
            add(KEY_CLIENT_SECRET, naverAuthProperty.secret)
        }
        val request = HttpEntity(body, httpHeaders)
        val response = restTemplate.postForObject(url, request, NaverTokens::class.java)
        println("Response: $response url: $url, headers: $httpHeaders")
        return response?.accessToken ?: throw WithaengException.of(WithaengExceptionType.AUTHENTICATION_FAILURE)
    }

    override fun requestOAuthInfo(accessToken: String): OAuthInfoResponse {
        val url = naverAuthProperty.url.api + AUTH_INFO_ENDPOINT
        val httpHeaders = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
            set(KEY_AUTHORIZATION, "${Constants.Authentication.BEARER_TOKEN_PREFIX_WITH_WHITESPACE}$accessToken")
        }
        val body = LinkedMultiValueMap<String, String>()
        val request = HttpEntity(body, httpHeaders)
        return restTemplate.postForObject(url, request, NaverInfoResponse::class.java)
            ?: throw WithaengException.of(WithaengExceptionType.AUTHENTICATION_FAILURE)
    }

    companion object {
        private const val AUTH_TOKEN_ENDPOINT = "/oauth2.0/token"
        private const val AUTH_INFO_ENDPOINT = "/v1/nid/me"

        private const val KEY_GRANT_TYPE = "grant_type"
        private const val KEY_CLIENT_ID = "client_id"
        private const val KEY_CLIENT_SECRET = "client_secret"
        private const val KEY_AUTHORIZATION = "Authorization"

        private const val VALUE_GRANT_TYPE = "authorization_code"
    }
}