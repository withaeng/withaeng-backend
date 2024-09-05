package com.withaeng.api.applicationservice.accompany

import com.withaeng.api.applicationservice.accompany.dto.*
import com.withaeng.api.common.Events
import com.withaeng.domain.accompany.AccompanyIncrementViewCountEvent
import com.withaeng.domain.accompany.AccompanyRepository
import com.withaeng.domain.accompany.AccompanyService
import com.withaeng.domain.accompanylike.AccompanyLikeService
import com.withaeng.domain.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccompanyApplicationService(
    private val accompanyService: AccompanyService,
    private val accompanyLikeService: AccompanyLikeService,
    private val userRepository: UserRepository,
    private val accompanyRepository: AccompanyRepository,
) {

    @Transactional
    fun create(request: CreateAccompanyServiceRequest): AccompanyResponse {
        return accompanyService.create(request.toDomainDto())
            .toAccompanyResponse(0L)
    }

    @Transactional
    fun update(request: UpdateAccompanyServiceRequest): AccompanyResponse {
        val accompanyDto = accompanyService.update(request.toDomainDto())
        val likeCount = countAccompanyLikeByAccompanyId(request.accompanyId)
        return accompanyDto.toAccompanyResponse(likeCount)
    }

    @Transactional(readOnly = true)
    fun detail(accompanyId: Long, userId: Long?): FindAccompanyResponse {
        increaseViewCount(accompanyId)
        val accompanyDto = accompanyService.detail(accompanyId)
            .toAccompanyResponse()

        if (isHost(userId, accompanyDto.userId)) {
            // TODO : Host인 경우 승인보류 유저 목록 추가 필요
        }

        return accompanyDto
    }

    // ##############################################################################################################

    @Transactional
    fun requestJoin(request: RequestJoinAccompanyCommand) {
        val guest = findUserById(request.userId)
        val accompany = findAccompanyById(request.accompanyId)
        accompany.requestJoin(guest.id)
    }

    @Transactional
    fun acceptJoin(request: AcceptJoinAccompanyCommand) {
        val host = findUserById(request.userId)
        val accompany = findAccompanyById(request.accompanyId)
        accompany.acceptJoin(host.id, request.joinRequestId)
    }

    private fun findUserById(userId: Long) =
        (userRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("User not found"))

    private fun findAccompanyById(accompanyId: Long) =
        (accompanyRepository.findByIdOrNull(accompanyId) ?: throw IllegalArgumentException("Accompany not found"))

    // ##############################################################################################################

    private fun isHost(loginUserId: Long?, userId: Long) =
        loginUserId == userId

    private fun increaseViewCount(accompanyId: Long) {
        Events.raise(AccompanyIncrementViewCountEvent(accompanyId))
    }

    fun retrieveAll(): List<AccompanyResponse> {
        val accompanyDtoList = accompanyService.findAll()
        // TODO: Bulk로 가져오는 방법을 고안
        return accompanyDtoList.map { accompanyDto ->
            accompanyDto.toAccompanyResponse(countAccompanyLikeByAccompanyId(accompanyDto.id))
        }
    }

    private fun countAccompanyLikeByAccompanyId(accompanyId: Long): Long {
        return accompanyLikeService.countByAccompanyId(accompanyId)
    }
}
