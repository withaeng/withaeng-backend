package com.withaeng.domain.accompany

import com.withaeng.domain.user.UserPreferAccompanyGender
import java.time.LocalDate

data class CreateAccompanyDto(
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

data class UpdateAccompanyDto(
    val accompanyId: Long,
    val content: String? = null,
    val tagIds: Set<Long>? = null,
)

data class AccompanyDto(
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
)

fun Accompany.toDto(): AccompanyDto = AccompanyDto(
    id = id,
    userId = hostId,
    title = title,
    content = content,
    destination = accompanyDestination,
    startTripDate = startTripDate,
    endTripDate = endTripDate,
    bannerImageUrl = bannerImageUrl,
    memberCount = memberCount,
    viewCount = accompanyStatistics?.viewCount ?: 0,
    openKakaoUrl = openKakaoUrl,
    startAccompanyAge = AccompanyAge.fromValue(startAccompanyAge),
    endAccompanyAge = AccompanyAge.fromValue(endAccompanyAge),
    preferGender = preferGender,
    tagIds = tagIds,
)

data class SearchAccompanyDto(
    var viewCntOrder: Boolean,    // 조회수 높은 순서
    var likeCntOrder: Boolean,    // 좋아요 높은 순서
    var startTripDate: LocalDate, // 동행 모집 시작일시
    var endTripDate: LocalDate,   // 동행 모집 마감일시
    var pageIndex: Long,
    var pageSize: Long
) {
    fun getCurrentPage(): Long {
        return (this.pageIndex - 1) * this.pageSize
    }
}