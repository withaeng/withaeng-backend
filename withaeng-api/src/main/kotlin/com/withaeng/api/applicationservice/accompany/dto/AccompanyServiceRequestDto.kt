package com.withaeng.api.applicationservice.accompany.dto

import com.withaeng.domain.accompany.*
import com.withaeng.domain.accompany.dto.CreateAccompanyDto
import com.withaeng.domain.accompany.dto.SearchAccompanyQuery
import com.withaeng.domain.accompany.dto.UpdateAccompanyDto
import com.withaeng.domain.user.UserPreferAccompanyGender
import java.time.LocalDate

data class CreateAccompanyServiceRequest(
    val userId: Long,
    val title: String,
    val content: String,
    val continent: String,
    val country: String,
    val city: String,
    val startTripDate: LocalDate,
    val endTripDate: LocalDate,
    val bannerImageUrl: String? = null,
    val memberCount: Long,
    val tags: Set<String>? = emptySet(),
    val openKakaoUrl: String,
    val startAccompanyAge: AccompanyAge,
    val endAccompanyAge: AccompanyAge,
    val preferGender: UserPreferAccompanyGender,
)

fun CreateAccompanyServiceRequest.toDomainDto(): CreateAccompanyDto = CreateAccompanyDto(
    userId = userId,
    title = title,
    content = content,
    destination = AccompanyDestination(
        continent = Continent.valueOf(continent),
        country = Country.valueOf(country),
        city = City.valueOf(city)
    ),
    startTripDate = startTripDate,
    endTripDate = endTripDate,
    bannerImageUrl = bannerImageUrl,
    memberCount = memberCount,
    tags = tags?.toSet(),
    openKakaoUrl = openKakaoUrl,
    startAccompanyAge = startAccompanyAge,
    endAccompanyAge = endAccompanyAge,
    preferGender = preferGender,
)

data class UpdateAccompanyServiceRequest(
    val accompanyId: Long,
    val userId: Long,
    val content: String? = null,
    val tags: Set<String>? = null,
)

fun UpdateAccompanyServiceRequest.toDomainDto(): UpdateAccompanyDto {
    return UpdateAccompanyDto(
        accompanyId = accompanyId,
        content = content,
        tags = tags?.toSet(),
    )
}

data class SearchAccompanyServiceRequest(
    val continent: Continent? = null,
    val country: Country? = null,
    val city: City? = null,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val minMemberCount: Int? = null,
    val maxMemberCount: Int? = null,
    val minAllowedAge: AccompanyAge? = null,
    val maxAllowedAge: AccompanyAge? = null,
    val preferGender: UserPreferAccompanyGender? = null,
)

fun SearchAccompanyServiceRequest.toQuery(): SearchAccompanyQuery {
    return SearchAccompanyQuery(
        continent = continent,
        country = country,
        city = city,
        startDate = startDate,
        endDate = endDate,
        minMemberCount = minMemberCount,
        maxMemberCount = maxMemberCount,
        minAllowedAge = minAllowedAge,
        maxAllowedAge = maxAllowedAge,
        preferGender = preferGender,
    )
}