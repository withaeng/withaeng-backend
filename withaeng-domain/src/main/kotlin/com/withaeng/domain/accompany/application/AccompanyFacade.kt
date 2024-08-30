package com.withaeng.domain.accompany.application

import com.withaeng.domain.accompany.AccompanyService
import com.withaeng.domain.accompany.dto.*
import com.withaeng.domain.accompanylike.AccompanyLikeService
import org.springframework.stereotype.Service

@Service
class AccompanyFacade(
    private val accompanyService: AccompanyService,
    private val accompanyLikeService: AccompanyLikeService,
) {

    fun create(command: CreateAccompanyCommand): AccompanyResponse {
        return accompanyService.create(command)
            .toAccompanyResponse(0L)
    }

    fun update(request: UpdateAccompanyServiceRequest): AccompanyResponse {
        val accompanyDto = accompanyService.update(request.toDomainDto())
        val likeCount = countAccompanyLikeByAccompanyId(request.accompanyId)
        return accompanyDto.toAccompanyResponse(likeCount)
    }

    fun detail(accompanyId: Long, userId: Long?): FindAccompanyDetailResult {
        increaseViewCount(accompanyId)
        val accompanyDto = accompanyService.detail(accompanyId)

        if (isHost(userId, accompanyDto.userId)) {
            // TODO : Host인 경우 승인보류 유저 목록 추가 필요
        }

        return accompanyDto
    }

    private fun increaseViewCount(accompanyId: Long) {
//        Events.raise(AccompanyIncrementViewCountEvent(accompanyId))
    }

    private fun isHost(loginUserId: Long?, userId: Long) =
        loginUserId == userId

    fun simples(): List<FindAccompanyDetailResult> {
        return accompanyService.findAll()
    }

    private fun countAccompanyLikeByAccompanyId(accompanyId: Long): Long {
        return accompanyLikeService.countByAccompanyId(accompanyId)
    }
}