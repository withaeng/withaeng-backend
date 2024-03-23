package com.travel.withaeng.common

import com.travel.withaeng.common.exception.WithaengExceptionType

data class ApiResponse<T>(
    val success: Boolean = true,
    val data: T? = null,
    val error: ApiErrorResponse? = null
) {
    companion object {
        fun success(): ApiResponse<Unit> {
            return ApiResponse(success = true, data = null)
        }

        fun <T> success(data: T): ApiResponse<T> {
            return ApiResponse(success = true, data = data)
        }

        fun fail(
            exceptionType: WithaengExceptionType,
            message: String?
        ): ApiResponse<Unit> {
            return ApiResponse(
                success = false,
                data = null,
                error = ApiErrorResponse(
                    code = exceptionType.errorCode,
                    message = message ?: exceptionType.message
                )
            )
        }
    }
}

data class ApiErrorResponse(val code: String, val message: String?)