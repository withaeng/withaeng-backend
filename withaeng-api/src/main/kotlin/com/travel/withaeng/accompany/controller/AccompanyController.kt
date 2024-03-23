package com.travel.withaeng.accompany.controller

import com.travel.withaeng.common.ApiResponse
import com.travel.withaeng.domain.accompany.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/accompany")
class AccompanyController(private val accompanyService: AccompanyService) {

    @PostMapping("")
    fun create(@RequestBody param : CreateAccompanyDTO) : ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse(true, accompanyService.createAccompany(param), null))
    }

    @PutMapping("")
    fun modify(@RequestBody param : ModifyAccompanyDTO) : ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse(true, accompanyService.modifyAccompany(param), null))
    }

    @GetMapping("/{accompanyId}")
    fun getOne(@PathVariable("accompanyId") accompanyId : Long) : ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse(true, accompanyService.getOne(accompanyId), null))
    }

    @PutMapping("/{accompanyId}/incr/viewCnt")
    fun incrViewCnt(@PathVariable("accompanyId") accompanyId : Long) : ResponseEntity<ApiResponse<Any>> {
        accompanyService.incrViewCnt(accompanyId)
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse(true, null, null))
    }

    @PutMapping("/{accompanyId}/decr/viewCnt")
    fun decrViewCnt(@PathVariable("accompanyId") accompanyId : Long) : ResponseEntity<ApiResponse<Any>> {
        accompanyService.decrViewCnt(accompanyId)
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse(true, null, null))
    }

    @GetMapping("/getList")
    fun getList(@RequestBody param : SearchAccompanyDTO) : ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse(true, accompanyService.getList(param), null))
    }

}