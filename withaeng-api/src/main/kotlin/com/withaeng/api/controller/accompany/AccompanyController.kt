package com.withaeng.api.controller.accompany

import com.withaeng.api.applicationservice.accompany.AccompanyApplicationService
import com.withaeng.api.applicationservice.accompany.dto.AcceptJoinAccompanyCommand
import com.withaeng.api.applicationservice.accompany.dto.AccompanyResponse
import com.withaeng.api.applicationservice.accompany.dto.FindAccompanyResponse
import com.withaeng.api.applicationservice.accompany.dto.RequestJoinAccompanyCommand
import com.withaeng.api.common.ApiResponse
import com.withaeng.api.controller.accompany.dto.CreateAccompanyRequest
import com.withaeng.api.controller.accompany.dto.UpdateAccompanyRequest
import com.withaeng.api.controller.accompany.dto.toServiceRequest
import com.withaeng.api.security.authentication.UserInfo
import com.withaeng.api.security.resolver.GetAuth
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
    private val accompanyApplicationService: AccompanyApplicationService,
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
        @RequestBody @Valid request: CreateAccompanyRequest,
    ): ApiResponse<AccompanyResponse> {
        return ApiResponse.success(
            accompanyApplicationService.create(request.toServiceRequest(userInfo.id))
        )
    }

    @Operation(summary = "Retrieve Accompany API", description = "동행 게시글 단건 조회 API")
    @GetMapping("/{accompanyId}")
    fun retrieve(
        @GetAuth userInfo: UserInfo?,
        @PathVariable("accompanyId") accompanyId: Long,
    ): ApiResponse<FindAccompanyResponse> {
        return ApiResponse.success(
            accompanyApplicationService.detail(accompanyId, userInfo?.id)
        )
    }

    @Operation(summary = "Retrieve All Accompany API", description = "모든 동행 게시글 조회 API")
    @GetMapping("/all")
    fun retrieveAll(): ApiResponse<List<AccompanyResponse>> {
        return ApiResponse.success(
            accompanyApplicationService.retrieveAll()
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
        @RequestBody @Valid param: UpdateAccompanyRequest,
    ): ApiResponse<AccompanyResponse> {
        return ApiResponse.success(
            accompanyApplicationService.update(
                param.toServiceRequest(
                    accompanyId = accompanyId,
                    userId = userInfo.id
                )
            )
        )
    }

    @PostMapping("/{accompanyId}/join-requests")
    fun requestJoin(
        @GetAuth userInfo: UserInfo,
        @PathVariable accompanyId: Long,
    ) {
        accompanyApplicationService.requestJoin(
            RequestJoinAccompanyCommand(
                userId = userInfo.id,
                accompanyId = accompanyId
            )
        )
    }

    @PatchMapping("/{accompanyId}/join-requests/{joinRequestId}")
    fun acceptJoin(
        @GetAuth userInfo: UserInfo,
        @PathVariable accompanyId: Long,
        @PathVariable joinRequestId: Long,
    ) {
        accompanyApplicationService.acceptJoin(
            AcceptJoinAccompanyCommand(
                userId = userInfo.id,
                accompanyId = accompanyId,
                joinRequestId = joinRequestId
            )
        )
    }
}