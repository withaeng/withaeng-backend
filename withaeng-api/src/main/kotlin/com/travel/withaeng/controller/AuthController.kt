package com.travel.withaeng.controller

import com.travel.withaeng.applicationservice.UserApplicationService
import com.travel.withaeng.common.ApiResponse
import com.travel.withaeng.infra.oauth.params.KakaoLoginParams
import com.travel.withaeng.infra.oauth.params.NaverLoginParams
import com.travel.withaeng.security.jwt.AuthToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(private val userApplicationService: UserApplicationService) {

    @PostMapping("/kakao")
    fun loginKakao(@RequestBody params: KakaoLoginParams): ApiResponse<AuthToken> {
        return ApiResponse.success(userApplicationService.login(params))
    }

    @PostMapping("/naver")
    fun loginNaver(@RequestBody params: NaverLoginParams): ApiResponse<AuthToken> {
        return ApiResponse.success(userApplicationService.login(params))
    }
}