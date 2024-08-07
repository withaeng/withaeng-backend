package com.travel.withaeng.applicationservice.accompanyreply

import com.travel.withaeng.applicationservice.accompanyreply.dto.*
import com.travel.withaeng.applicationservice.common.PagingResponse
import com.travel.withaeng.applicationservice.common.toPaging
import com.travel.withaeng.common.exception.WithaengException
import com.travel.withaeng.common.exception.WithaengExceptionType
import com.travel.withaeng.domain.accompanyreply.AccompanyReplyService
import com.travel.withaeng.domain.accompanyreplylike.AccompanyReplyLikeService
import com.travel.withaeng.domain.user.UserService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccompanyReplyApplicationService(
    val userService: UserService,
    val accompanyReplyService: AccompanyReplyService,
    val accompanyReplyLikeService: AccompanyReplyLikeService
) {

    @Transactional
    fun create(request: CreateAccompanyReplyServiceRequest): AccompanyReplyResponse {
        val userSimpleDto = userService.findById(request.userId)
        return accompanyReplyService.create(
            accompanyId = request.accompanyId,
            userId = request.userId,
            content = request.content,
            parentId = request.parentId
        ).toResponse(userSimpleDto)
    }

    fun search(accompanyId: Long, pageable: Pageable): PagingResponse<List<AccompanyReplyResponse>> {
        val accompanyReplyPage = accompanyReplyService.search(accompanyId, pageable)
        val contents = accompanyReplyPage.content
        // TODO: Fix getting like count & user logic for using bulk from single
        val accompanyReplyResponseList = contents.map { replyDto ->
            val likeCount = countAccompanyReplyLikeCount(replyDto.id)
            val userSimpleDto = userService.findById(replyDto.userId)
            replyDto.toResponse(userSimpleDto, likeCount)
        }
        return PagingAccompanyReplyResponse(accompanyReplyResponseList, accompanyReplyPage.toPaging())
    }

    @Transactional
    fun update(request: UpdateAccompanyReplyServiceRequest): AccompanyReplyResponse {
        val accompanyReplyDto = accompanyReplyService.findById(request.accompanyReplyId)
        val userSimpleDto = userService.findById(request.userId)
        validateCreator(accompanyReplyDto.userId, request.userId)
        val updated = accompanyReplyService.update(accompanyReplyDto.id, request.content)
        val likeCount = accompanyReplyLikeService.countAccompanyReplyLikeCount(accompanyReplyDto.id)
        return updated.toResponse(userSimpleDto, likeCount)
    }

    @Transactional
    fun delete(userId: Long, accompanyReplyId: Long) {
        val accompanyReplyDto = accompanyReplyService.findById(accompanyReplyId)
        validateCreator(accompanyReplyDto.userId, userId)
        accompanyReplyService.delete(accompanyReplyDto.id)
    }

    @Transactional
    fun updateSubReply(request: UpdateAccompanyReplyServiceRequest): AccompanyReplyResponse {
        val accompanyReplyDto = accompanyReplyService.findById(request.accompanyReplyId)
        validateCreator(accompanyReplyDto.userId, request.userId)
        val updated =
            accompanyReplyService.updateSubReply(accompanyReplyDto.id, accompanyReplyDto.parentId!!, request.content)
        val likeCount = countAccompanyReplyLikeCount(accompanyReplyDto.id)
        val userSimpleDto = userService.findById(request.userId)
        return updated.toResponse(userSimpleDto, likeCount)
    }

    private fun countAccompanyReplyLikeCount(replyId: Long) =
        accompanyReplyLikeService.countAccompanyReplyLikeCount(replyId)

    private fun validateCreator(requestUserId: Long, createUserId: Long) {
        if (requestUserId != createUserId) {
            throw WithaengException.of(
                type = WithaengExceptionType.ACCESS_DENIED,
                message = "댓글 작성자가 아닌 사용자가 수정할 수 없습니다."
            )
        }
    }
}
