package com.withaeng.api.accompany

import com.withaeng.api.accompany.dto.GetAccompanyDetailResponse
import com.withaeng.api.accompany.dto.PostCreateAccompanyRequest
import com.withaeng.api.accompany.dto.PutUpdateAccompanyRequest
import com.withaeng.api.accompany.dto.toCommand
import com.withaeng.api.common.ApiResponse
import com.withaeng.api.security.authentication.UserInfo
import com.withaeng.api.security.resolver.GetAuth
import com.withaeng.domain.accompany.application.AccompanyFacade
import com.withaeng.domain.accompany.dto.AccompanyResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "Accompany", description = "동행 API")
@RestController
@RequestMapping("/api/v1/accompany")
class AccompanyController(
    private val accompanyApplicationService: AccompanyFacade
) {

    @Operation(
        summary = "Create Accompany API",
        description = "동행 게시글 생성 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    fun create(
        @GetAuth userInfo: UserInfo,
        @RequestBody @Valid request: PostCreateAccompanyRequest
    ): ApiResponse<AccompanyResponse> {
        return ApiResponse.success(
            accompanyApplicationService.create(request.toCommand(userInfo.id))
        )
    }

    @Operation(summary = "Retrieve Accompany API", description = "동행 게시글 단건 조회 API")
    @GetMapping("/{accompanyId}")
    fun retrieve(
        @GetAuth userInfo: UserInfo?,
        @PathVariable("accompanyId") accompanyId: Long
    ): ApiResponse<GetAccompanyDetailResponse> {
        val result = accompanyApplicationService.detail(accompanyId, userInfo?.id)
        return ApiResponse.success(
            GetAccompanyDetailResponse.of(result)
        )
    }

    @Operation(summary = "Retrieve All Accompany API", description = "모든 동행 게시글 조회 API")
    @GetMapping
    fun retrieveAll(): ApiResponse<List<GetAccompanyDetailResponse>> {
        val result = accompanyApplicationService.simples()
            .map(GetAccompanyDetailResponse::of)
        return ApiResponse.success(
            result
        )
    }

    @Operation(
        summary = "Update Accompany API",
        description = "동행 게시글 수정 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PutMapping("/{accompanyId}")
    fun update(
        @GetAuth userInfo: UserInfo,
        @PathVariable accompanyId: Long,
        @RequestBody @Valid param: PutUpdateAccompanyRequest
    ): ApiResponse<AccompanyResponse> {
        return ApiResponse.success(
            accompanyApplicationService.update(
                param.toCommand(
                    accompanyId = accompanyId,
                    userId = userInfo.id
                )
            )
        )
    }
}