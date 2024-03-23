package com.travel.withaeng.security.jwt

data class AuthToken(val accessToken: String, val refreshToken: String, val grantType: String, val expiresIn: Long)