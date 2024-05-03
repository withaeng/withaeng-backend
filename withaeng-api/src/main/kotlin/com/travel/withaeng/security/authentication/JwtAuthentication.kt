package com.travel.withaeng.security.authentication

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class JwtAuthentication(private val userInfo: UserInfo) : Authentication {

    private var isAuthenticated = true

    override fun getName(): String = userInfo.nickname

    override fun getAuthorities(): Collection<GrantedAuthority> = userInfo.roles
        .map { userRole -> SimpleGrantedAuthority(userRole.role) }

    override fun getCredentials(): Any = userInfo.id

    override fun getDetails(): Any = userInfo.toString()

    override fun getPrincipal(): Any = userInfo

    override fun isAuthenticated(): Boolean = isAuthenticated

    override fun setAuthenticated(isAuthenticated: Boolean) {
        this.isAuthenticated = isAuthenticated
    }

}