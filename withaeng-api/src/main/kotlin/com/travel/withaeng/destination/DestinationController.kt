package com.travel.withaeng.destination

import com.travel.withaeng.common.ApiResponse
import com.travel.withaeng.domain.destination.DestinationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/destination")
class DestinationController(private val destinationService: DestinationService) {


    @GetMapping("/getList")
    fun getList() : ResponseEntity<ApiResponse<Any>>{
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse(true, destinationService.getDestinationList(), null))
    }
}