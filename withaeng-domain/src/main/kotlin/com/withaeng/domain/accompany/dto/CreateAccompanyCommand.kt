package com.withaeng.domain.accompany.dto

import com.withaeng.domain.accompany.AccompanyAge
import com.withaeng.domain.accompany.AccompanyDestination
import com.withaeng.domain.accompany.UpdateAccompanyDto
import com.withaeng.domain.user.UserPreferAccompanyGender
import java.time.LocalDate

data class CreateAccompanyCommand(
    val userId: Long,
    val title: String,
    val content: String,
    val destination: AccompanyDestination,
    val startTripDate: LocalDate,
    val endTripDate: LocalDate,
    val bannerImageUrl: String? = null,
    val memberCount: Long,
    val tagIds: Set<Long>? = emptySet(),
    val openKakaoUrl: String,
    val startAccompanyAge: AccompanyAge,
    val endAccompanyAge: AccompanyAge,
    val preferGender: UserPreferAccompanyGender,
)

data class UpdateAccompanyServiceRequest(
    val accompanyId: Long,
    val userId: Long,
    val content: String? = null,
    val tagIds: Set<Long>? = null,
)

fun UpdateAccompanyServiceRequest.toDomainDto(): UpdateAccompanyDto {
    return UpdateAccompanyDto(
        accompanyId = accompanyId,
        content = content,
        tagIds = tagIds?.toSet(),
    )
}

data class SearchAccompanyServiceRequest(
    val orderBy: String,
    val isDescending: Boolean,
    val pageIndex: Long,
    val pageSize: Long
)