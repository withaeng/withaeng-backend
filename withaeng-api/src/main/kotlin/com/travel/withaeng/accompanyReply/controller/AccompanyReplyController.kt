package com.travel.withaeng.accompanyReply.controller

import com.travel.withaeng.common.ApiResponse
import com.travel.withaeng.domain.accompanyreply.AccompanyReplyService
import com.travel.withaeng.domain.accompanyreply.CreateAccompanyReplyDTO
import com.travel.withaeng.domain.accompanyreply.DeleteAccompanyReplyDTO
import com.travel.withaeng.domain.accompanyreply.ModifyAccompanyReplyDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/accompany{accompanyId}/reply")
class AccompanyReplyController(private val accompanyReplyService: AccompanyReplyService){

    @PostMapping("")
    fun create(@RequestBody param : CreateAccompanyReplyDTO) : ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse(true, accompanyReplyService.createAccompanyReply(param), null))
    }

    @PutMapping("")
    fun create(@RequestBody param : ModifyAccompanyReplyDTO) : ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse(true, accompanyReplyService.modifyAccompanyReply(param), null))
    }

    @DeleteMapping("")
    fun create(@RequestBody param : DeleteAccompanyReplyDTO) : ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse(true, accompanyReplyService.deleteAccompanyReply(param), null))
    }

    @GetMapping("/getList")
    fun create(@RequestBody param : Long) : ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse(true, accompanyReplyService.getList(param), null))
    }

}