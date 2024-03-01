package com.travel.withaeng.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.travel.withaeng.common.exception.WithaengException
import com.travel.withaeng.common.exception.WithaengExceptionType
import com.travel.withaeng.security.authentication.UserInfo
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.security.Key
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import java.util.*

class JwtProvider(private val mapper: ObjectMapper, key: String) {

    private val secretKey: Key
    private val jwtParser: JwtParser

    init {
        val encodedKey = Base64.getEncoder().encodeToString(key.toByteArray())
        secretKey = Keys.hmacShaKeyFor(encodedKey.toByteArray())
        jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build()
    }

    fun createToken(userInfo: UserInfo, expireTime: Long? = null): String {
        val issuerDateTime = LocalDateTime.now(ASIA_SEOUL_ZONE_ID)
        val expiredDateTime = expireTime?.let { issuerDateTime.plus(it, ChronoUnit.MILLIS) }
            ?: issuerDateTime.plusDays(TOKEN_EXPIRE_DAY)
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setSubject("jwt-user-${userInfo.nickname}")
            .setAudience("${userInfo.providerUniqueKey}|${userInfo.id}|${userInfo.nickname}")
            .setIssuer("https://github.com/withaeng/withaeng-backend")
            .setIssuedAt(issuerDateTime.convertToDate())
            .setExpiration(expiredDateTime.convertToDate())
            .claim(KEY_CLAIM_INFO, userInfo)
            .signWith(secretKey)
            .compact()
    }

    fun extractUserInfoFromToken(token: String): UserInfo {
        return jwtParser.parseClaimsJws(token).body?.let { claims ->
            mapper.convertValue(claims[KEY_CLAIM_INFO], UserInfo::class.java)
        } ?: throw WithaengException.of(WithaengExceptionType.INVALID_USER_AUTH_TOKEN)
    }

    private fun LocalDateTime.convertToDate(): Date = Date.from(this.toInstant(ASIA_SEOUL_ZONE_OFFSET))

    companion object {
        private const val KEY_CLAIM_INFO = "info"

        private val ASIA_SEOUL_ZONE_ID = ZoneId.of("Asia/Seoul")
        private val ASIA_SEOUL_ZONE_OFFSET = ZoneOffset.of("+09:00")

        private const val TOKEN_EXPIRE_DAY = 30L
    }
}