package com.travel.withaeng.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.travel.withaeng.security.jwt.JwtProvider
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(AuthProperty::class, KakaoAuthProperty::class, NaverAuthProperty::class)
class AuthConfig {

    @Bean
    fun jwtProvider(mapper: ObjectMapper, authProperty: AuthProperty): JwtProvider {
        return JwtProvider(mapper, authProperty.jwtSecretKey)
    }
}

@ConfigurationProperties(prefix="witheang.auth")
data class AuthProperty(val jwtSecretKey: String)

@ConfigurationProperties(prefix = "oauth.kakao")
data class KakaoAuthProperty(val clientId: String, val url: OAuthUrl)

@ConfigurationProperties(prefix = "oauth.naver")
data class NaverAuthProperty(val clientId: String, val secret: String, val url: OAuthUrl)

data class OAuthUrl(val auth: String, val api: String)
