package com.travel.withaeng.accompanyReply.controller

import com.travel.withaeng.domain.accompanyreply.AccompanyReplyService
import com.travel.withaeng.domain.accompanyreply.CreateAccompanyReplyDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/accompany{accompanyId}/reply")
class AccompanyReplyController(private val accompanyReplyService: AccompanyReplyService){

    /*@PostMapping("")
    fun create(@RequestBody param : CreateAccompanyReplyDTO) : ResponseEntity<Long> {
        return ResponseEntity.status(HttpStatus.CREATED).body(accompanyReplyService.createAccompanyReply(param))
    }*/

}