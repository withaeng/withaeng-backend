package com.withaeng.api.accompany.dto

import com.withaeng.domain.accompany.AccompanyDestination
import com.withaeng.domain.accompany.dto.FindAccompanyDetailResult
import com.withaeng.domain.user.UserPreferAccompanyGender
import java.time.LocalDate

data class GetAccompanyDetailResponse(
    val id: Long,
    val userId: Long,
    val title: String,
    val content: String,
    val destination: AccompanyDestination,
    val startTripDate: LocalDate,
    val endTripDate: LocalDate,
    val bannerImageUrl: String? = null,
    val memberCount: Long,
    val viewCount: Long,
    val openKakaoUrl: String,
    val startAccompanyAge: Int,
    val endAccompanyAge: Int,
    val preferGender: UserPreferAccompanyGender,
    val tagIds: Set<Long>? = null,
) {
    companion object {
        fun of(result: FindAccompanyDetailResult): GetAccompanyDetailResponse {
            return GetAccompanyDetailResponse(
                id = result.id,
                userId = result.userId,
                title = result.title,
                content = result.content,
                destination = result.destination,
                startTripDate = result.startTripDate,
                endTripDate = result.endTripDate,
                bannerImageUrl = result.bannerImageUrl,
                memberCount = result.memberCount,
                viewCount = result.viewCount,
                openKakaoUrl = result.openKakaoUrl,
                startAccompanyAge = result.startAccompanyAge.value,
                endAccompanyAge = result.endAccompanyAge.value,
                preferGender = result.preferGender,
                tagIds = result.tagIds,
            )
        }
    }
}

