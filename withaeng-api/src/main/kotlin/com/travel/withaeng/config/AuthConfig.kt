package com.travel.withaeng.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.travel.withaeng.security.jwt.JwtProvider
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
//@EnableConfigurationProperties(AuthProperty::class)
class AuthConfig {

    @Bean
    fun jwtProvider(mapper: ObjectMapper): JwtProvider {
        return JwtProvider(mapper, "ENC(i5HqamjyyaTwDSyiHXoAxJrP11XyQ8mArvAUI1RD15tteCbIEnzVVp+qcYB45xDkybBZmjY79HDp/Ci5YkkSyg==)")
    }
}

//@ConfigurationProperties(prefix = "witheang.auth")
//data class AuthProperty(val jwtSecretKey: String)
