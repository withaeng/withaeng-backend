package com.travel.withaeng.accompany.controller

import com.travel.withaeng.domain.accompany.AccompanyDto
import com.travel.withaeng.domain.accompany.AccompanyService
import com.travel.withaeng.domain.accompany.CreateAccompanyDTO
import com.travel.withaeng.domain.accompany.ReadAccompanyDTO
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.Objects

@RestController
@RequestMapping("/api/v1/accompany")
class AccompanyController(private val accompanyService: AccompanyService) {

    @PostMapping("")
    fun create(@RequestBody param : CreateAccompanyDTO) : ResponseEntity<Long> {
        return ResponseEntity.status(HttpStatus.CREATED).body(accompanyService.createAccompany(param))
    }

    /*@PutMapping("")
    fun create(@RequestBody param : ModifyAccompanyDTO) : ResponseEntity<Long> {
        val accompanyId = accompanyService.createAccompany(param)
        return ResponseEntity.status(HttpStatus.CREATED).body(accompanyId)
    }*/

    @GetMapping("/{accompanyId}")
    fun getOne(@PathVariable("accompanyId") accompanyId : Long) : ResponseEntity<ReadAccompanyDTO> {
        return ResponseEntity.status(HttpStatus.OK).body(accompanyService.getOne(accompanyId))
    }

    @PutMapping("/{accompanyId}/incr/viewCnt")
    fun incrViewCnt(@PathVariable("accompanyId") accompanyId : Long) : ResponseEntity<Long> {
        return ResponseEntity.status(HttpStatus.OK).body(accompanyService.incrViewCnt(accompanyId))
    }

    /*@GetMapping("/getList")
    fun getList(@RequestBody param : SearchAccompanyDTO) : ResponseEntity<List<ReadAccompanyDTO>> {
        return ResponseEntity.status(HttpStatus.OK).body(accompanyService.getList(param))
    }*/

}