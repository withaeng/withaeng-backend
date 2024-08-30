package com.withaeng.domain.accompany.dto

import com.withaeng.domain.accompany.Accompany
import com.withaeng.domain.accompany.AccompanyAge
import com.withaeng.domain.accompany.AccompanyDestination
import com.withaeng.domain.user.UserPreferAccompanyGender
import java.time.LocalDate

data class FindAccompanyDetailResult(
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
    val startAccompanyAge: AccompanyAge,
    val endAccompanyAge: AccompanyAge,
    val preferGender: UserPreferAccompanyGender,
    val tagIds: Set<Long>? = null,
) {
    companion object {
        fun of(accompany: Accompany): FindAccompanyDetailResult {
            return FindAccompanyDetailResult(
                id = accompany.id,
                userId = accompany.userId,
                title = accompany.title,
                content = accompany.content,
                destination = accompany.accompanyDestination,
                startTripDate = accompany.startTripDate,
                endTripDate = accompany.endTripDate,
                bannerImageUrl = accompany.bannerImageUrl,
                memberCount = accompany.memberCount,
                viewCount = accompany.accompanyStatistics?.viewCount ?: 0,
                openKakaoUrl = accompany.openKakaoUrl,
                startAccompanyAge = AccompanyAge.fromValue(accompany.startAccompanyAge),
                endAccompanyAge = AccompanyAge.fromValue(accompany.endAccompanyAge),
                preferGender = accompany.preferGender,
                tagIds = accompany.tagIds,
            )
        }
    }
}

