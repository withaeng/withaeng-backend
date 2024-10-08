package com.withaeng.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.withaeng.api.security.jwt.JwtAgent
import com.withaeng.api.security.jwt.JwtAgentImpl
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(AuthProperty::class)
class AuthConfig {
    @Bean
    fun jwtAgent(mapper: ObjectMapper, authProperty: AuthProperty): JwtAgent {
        return JwtAgentImpl(
            objectMapper = mapper,
            issuer = authProperty.jwtIssuer,
            key = authProperty.jwtSecretKey
        )
    }
}

@ConfigurationProperties(prefix = "withaeng.auth")
data class AuthProperty(val jwtSecretKey: String, val jwtIssuer: String)
