package com.travel.withaeng.domain.accompany

import org.springframework.data.jpa.repository.JpaRepository

interface AccompanyDestinationRepository : JpaRepository<AccompanyDestinationEntity, Long> {
    fun findByAccompanyId(accompanyId : Long) : AccompanyDestinationEntity
}