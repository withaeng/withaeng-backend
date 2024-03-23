package com.travel.withaeng.domain.accompany

import org.springframework.data.jpa.repository.JpaRepository

interface AccompanyTagRepository : JpaRepository<AccompanyTagEntity, Long> {

    fun findByAccompanyId(accompanyId : Long) : List<AccompanyTagEntity>?

    fun deleteByAccompanyId(accompanyId: Long)
}