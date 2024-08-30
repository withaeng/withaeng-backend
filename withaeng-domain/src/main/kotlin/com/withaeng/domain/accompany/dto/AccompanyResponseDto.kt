package com.withaeng.domain.accompany.dto

import com.withaeng.domain.accompany.AccompanyDestination
import com.withaeng.domain.accompany.AccompanyDto
import com.withaeng.domain.user.UserPreferAccompanyGender
import java.time.LocalDate

data class AccompanyResponse(
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
    val likeCount: Long,
    val tagIds: Set<Long>? = null,
    val openKakaoUrl: String? = null,
    val startAccompanyAge: Int,
    val endAccompanyAge: Int,
    val preferGender: UserPreferAccompanyGender,
)

fun AccompanyDto.toAccompanyResponse(likeCount: Long): AccompanyResponse = AccompanyResponse(
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
    likeCount = likeCount,
    openKakaoUrl = openKakaoUrl,
    startAccompanyAge = startAccompanyAge.value,
    endAccompanyAge = endAccompanyAge.value,
    preferGender = preferGender,
    tagIds = tagIds,
)