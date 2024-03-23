package com.travel.withaeng.accompanyLike.controller

import com.travel.withaeng.domain.accompany.CreateAccompanyDTO
import com.travel.withaeng.domain.accompanylike.AccompanyLikeService
import com.travel.withaeng.domain.accompanylike.CreateAccompanyLikeDTO
import com.travel.withaeng.domain.accompanylike.DeleteAccompanyLikeDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/accompany/{accompanyId}/like")
class AccompanyLikeController(private val accompanyLikeService: AccompanyLikeService) {

    @PostMapping("")
    fun create(@RequestBody param : CreateAccompanyLikeDTO) : ResponseEntity<Long> {
        return ResponseEntity.status(HttpStatus.CREATED).body(accompanyLikeService.createAccompanyLike(param))
    }

    @DeleteMapping("")
    fun delete(@RequestBody param : DeleteAccompanyLikeDTO) : ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.OK).body("")
    }

}