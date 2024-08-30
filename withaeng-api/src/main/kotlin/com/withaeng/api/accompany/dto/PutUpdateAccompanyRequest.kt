package com.withaeng.api.accompany.dto

import com.withaeng.domain.accompany.dto.UpdateAccompanyServiceRequest
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "[Request] 동행 게시글 수정")
data class PutUpdateAccompanyRequest(
    @Schema(description = "동행 게시글 내용")
    val content: String? = null,

    @Schema(description = "동행 게시글에 부착할 태그 아이디 리스트")
    val tagIds: Set<Long>? = null,
)

fun PutUpdateAccompanyRequest.toCommand(
    accompanyId: Long,
    userId: Long
): UpdateAccompanyServiceRequest = UpdateAccompanyServiceRequest(
    accompanyId = accompanyId,
    userId = userId,
    content = content,
    tagIds = tagIds,
)