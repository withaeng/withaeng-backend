package com.travel.withaeng.infra.oauth.service

import com.travel.withaeng.common.exception.WithaengException
import com.travel.withaeng.common.exception.WithaengExceptionType
import com.travel.withaeng.infra.oauth.client.OAuthClient
import com.travel.withaeng.infra.oauth.params.OAuthLoginParams
import com.travel.withaeng.infra.oauth.response.OAuthInfoResponse
import org.springframework.stereotype.Service

@Service
class OAuthService(clients: List<OAuthClient>) {

    private val clientsMap = clients.associateBy { client -> client.getSocialType() }

    fun request(params: OAuthLoginParams): OAuthInfoResponse {
        val client = clientsMap[params.getSocialType()]
        return client?.requestAccessToken(params)?.let(client::requestOAuthInfo)
            ?: throw WithaengException.of(
                type = WithaengExceptionType.SYSTEM_FAIL,
                message = "failed to login ${params.getSocialType()}"
            )
    }

}