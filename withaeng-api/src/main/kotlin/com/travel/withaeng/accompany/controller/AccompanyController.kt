package com.travel.withaeng.accompany.controller

import com.travel.withaeng.domain.accompany.AccompanyDto
import com.travel.withaeng.domain.accompany.AccompanyService
import com.travel.withaeng.domain.accompany.CreateAccompanyDTO
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/accompany")
class AccompanyController(private val accompanyService: AccompanyService) {

    @PostMapping("")
    fun createAccompany(@RequestBody param : CreateAccompanyDTO) : ResponseEntity<Long> {
        val accompanyId = accompanyService.createAccompany(param);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(accompanyId);
    }


}