package com.withaeng.domain.accompany

import com.querydsl.core.annotations.QueryProjection
import com.withaeng.domain.accompany.dto.FindAccompanyDetailResult
import com.withaeng.domain.user.UserPreferAccompanyGender
import java.time.LocalDate

data class FindAccompanyDto @QueryProjection constructor(
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
    val tagIds: Set<Long>? = emptySet(),
    val likeCount: Long = 0,
    val author: FindAccompanyUserInfoDto,
) {
    fun toResult(): FindAccompanyDetailResult {
        return FindAccompanyDetailResult(
            id = id,
            userId = userId,
            title = title,
            content = content,
            destination = destination,
            startTripDate = startTripDate,
            endTripDate = endTripDate,
            bannerImageUrl = bannerImageUrl,
            memberCount = memberCount,
            viewCount = viewCount,
            openKakaoUrl = openKakaoUrl,
            startAccompanyAge = AccompanyAge.fromValue(startAccompanyAge),
            endAccompanyAge = AccompanyAge.fromValue(endAccompanyAge),
            preferGender = preferGender,
            tagIds = tagIds,
        )
    }
}