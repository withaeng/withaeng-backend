package com.travel.withaeng.applicationservice.auth

import com.travel.withaeng.applicationservice.auth.dto.SignInServiceRequest
import com.travel.withaeng.applicationservice.auth.dto.SignUpServiceRequest
import com.travel.withaeng.applicationservice.auth.dto.ValidateEmailServiceRequest
import com.travel.withaeng.domain.user.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AuthApplicationService(private val userService: UserService) {

    @Transactional(readOnly = false)
    fun signIn(request: SignInServiceRequest) {

    }

    @Transactional(readOnly = false)
    fun signUp(request: SignUpServiceRequest) {

    }

    @Transactional(readOnly = false)
    fun validateEmail(request: ValidateEmailServiceRequest) {

    }
}