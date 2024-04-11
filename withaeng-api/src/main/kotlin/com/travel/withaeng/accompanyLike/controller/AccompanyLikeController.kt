package com.travel.withaeng.accompanyLike.controller

import com.travel.withaeng.common.ApiResponse
import com.travel.withaeng.domain.accompany.CreateAccompanyDTO
import com.travel.withaeng.domain.accompanylike.AccompanyLikeService
import com.travel.withaeng.domain.accompanylike.CreateAccompanyLikeDTO
import com.travel.withaeng.domain.accompanylike.DeleteAccompanyLikeDTO
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/accompany/{accompanyId}/like")
class AccompanyLikeController(private val accompanyLikeService: AccompanyLikeService) {

    @PostMapping("")
    fun create(@RequestBody @Valid param : CreateAccompanyLikeDTO) : ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse(true, accompanyLikeService.createAccompanyLike(param), null))
    }

    @DeleteMapping("")
    fun delete(@RequestBody @Valid param : DeleteAccompanyLikeDTO) : ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse(true,accompanyLikeService.deleteAccompanyLike(param), null))
    }

}