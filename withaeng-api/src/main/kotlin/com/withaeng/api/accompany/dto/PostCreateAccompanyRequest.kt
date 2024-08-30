package com.withaeng.api.accompany.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.withaeng.domain.accompany.*
import com.withaeng.domain.accompany.dto.CreateAccompanyCommand
import com.withaeng.domain.user.UserPreferAccompanyGender
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "[Request] 동행 게시글 생성")
data class PostCreateAccompanyRequest(
    @Schema(description = "동행 게시글 제목")
    val title: String,

    @Schema(description = "동행 게시글 내용")
    val content: String,

    @Schema(description = "동행 목적지의 대륙")
    val continent: String,

    @Schema(description = "동행 목적지의 나라")
    val country: String,

    @Schema(description = "동행 목적지의 도시")
    val city: String,

    @Schema(description = "동행 시작 날짜 (1999-01-01)")
    val startTripDate: LocalDate,

    @Schema(description = "동행 종료 날짜 (1999-01-01)")
    val endTripDate: LocalDate,

    @Schema(description = "동행 게시글 배너 이미지 URL")
    val bannerImageUrl: String? = null,

    @Schema(description = "동행 멤버수")
    val memberCount: Long,

    @Schema(description = "동행 게시글에 부착할 태그 아이디 리스트")
    val tagIds: Set<Long>? = emptySet(),

    @Schema(description = "동행 게시글에 게시된 오픈 카카오톡 URL")
    val openKakaoUrl: String,

    @Schema(description = "동행 시작 연령(누구나 가능의 경우 0)")
    @JsonDeserialize(using = AccompanyAgeDeserializer::class)
    val startAccompanyAge: AccompanyAge,

    @Schema(description = "동행 시작 연령(누구나 가능의 경우 99)")
    @JsonDeserialize(using = AccompanyAgeDeserializer::class)
    val endAccompanyAge: AccompanyAge,

    @Schema(description = "동행 선호 성별")
    val preferGender: UserPreferAccompanyGender,
)

@Schema(description = "[Request] 동행 게시글 수정")
fun PostCreateAccompanyRequest.toCommand(
    userId: Long
): CreateAccompanyCommand = CreateAccompanyCommand(
    userId = userId,
    title = title,
    destination = AccompanyDestination(
        continent = Continent.valueOf(continent),
        country = Country.valueOf(country),
        city = City.valueOf(city)
    ),
    content = content,
//    continent = continent,
//    country = country,
//    city = city,
    startTripDate = startTripDate,
    endTripDate = endTripDate,
    bannerImageUrl = bannerImageUrl,
    memberCount = memberCount,
    tagIds = tagIds,
    openKakaoUrl = openKakaoUrl,
    startAccompanyAge = startAccompanyAge,
    endAccompanyAge = endAccompanyAge,
    preferGender = preferGender,
)