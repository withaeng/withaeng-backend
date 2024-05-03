package com.travel.withaeng.controller.auth

import com.travel.withaeng.applicationservice.auth.AuthApplicationService
import com.travel.withaeng.controller.auth.dto.AuthSignInRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/auth")
class AuthController(private val authApplicationService: AuthApplicationService) {

    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: AuthSignInRequest) {

    }

    @PostMapping("/sign-in")
    fun signUp() {

    }

    @PostMapping("/validate-email")
    fun validateEmail() {

    }

    @PostMapping("/add-details")
    fun addDetails() {

    }
}