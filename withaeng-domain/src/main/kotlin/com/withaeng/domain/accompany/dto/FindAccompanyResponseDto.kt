package com.withaeng.domain.accompany.dto

import com.withaeng.domain.accompany.AccompanyDestination
import com.withaeng.domain.accompany.FindAccompanyDto
import com.withaeng.domain.accompany.FindAccompanyUserInfoDto
import com.withaeng.domain.user.UserPreferAccompanyGender
import java.time.LocalDate

data class FindAccompanyResponse(
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
    val approvalPendingUsers: Set<FindAccompanyUserInfoDto>? = null,
)

fun FindAccompanyDto.toAccompanyResponse() = FindAccompanyResponse(
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
    startAccompanyAge = startAccompanyAge,
    endAccompanyAge = endAccompanyAge,
    preferGender = preferGender,
    tagIds = tagIds,
    author = author,
)