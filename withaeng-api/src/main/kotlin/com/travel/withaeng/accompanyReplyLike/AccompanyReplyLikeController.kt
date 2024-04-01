package com.travel.withaeng.accompanyReplyLike

import com.travel.withaeng.common.ApiResponse
import com.travel.withaeng.domain.accompanylike.AccompanyLikeService
import com.travel.withaeng.domain.accompanylike.CreateAccompanyLikeDTO
import com.travel.withaeng.domain.accompanylike.DeleteAccompanyLikeDTO
import com.travel.withaeng.domain.accompanyreplylike.AccompanyReplyLikeService
import com.travel.withaeng.domain.accompanyreplylike.CreateAccompanyReplyLikeDTO
import com.travel.withaeng.domain.accompanyreplylike.DeleteAccompanyReplyLikeDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/accompany{accompanyId}/reply/{replyId}/like")
class AccompanyReplyLikeController(private val accompanyReplyLikeService: AccompanyReplyLikeService) {

    @PostMapping("")
    fun create(@RequestBody param : CreateAccompanyReplyLikeDTO) : ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse(true, accompanyReplyLikeService.createAccompanyReplyLike(param), null))
    }

    @DeleteMapping("")
    fun delete(@RequestBody param : DeleteAccompanyReplyLikeDTO) : ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse(true, accompanyReplyLikeService.deleteAccompanyReplyLike(param), null))
    }

}