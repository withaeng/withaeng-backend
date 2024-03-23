package com.travel.withaeng.domain.accompany

import org.springframework.data.jpa.repository.JpaRepository

interface AccompanyDetailRepository : JpaRepository<AccompanyDetailEntity, Long> {
    fun findByAccompanyId(accompanyId : Long) : AccompanyDetailEntity

}