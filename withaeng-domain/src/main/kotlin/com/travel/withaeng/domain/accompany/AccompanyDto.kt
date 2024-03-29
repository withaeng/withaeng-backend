package com.travel.withaeng.domain.accompany

import java.time.LocalDate
import java.time.LocalDateTime

data class AccompanyDto(
    val userId: Long,
    val title: String,
    val content: String,
    val destination: Destination,
    val startTripDate: LocalDate,
    val endTripDate: LocalDate,
    val bannerImageUrl: String?,
    val viewCounts: Long,
    val createdAt: LocalDateTime
)

fun Accompany.toDto(): AccompanyDto = AccompanyDto(
    userId = userId,
    title = title,
    content = content,
    destination = destination,
    startTripDate = startTripDate,
    endTripDate = endTripDate,
    bannerImageUrl = bannerImageUrl,
    viewCounts = viewCounts,
    createdAt = createdAt
)