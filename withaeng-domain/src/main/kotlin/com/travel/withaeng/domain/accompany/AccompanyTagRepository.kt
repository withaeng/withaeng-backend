package com.travel.withaeng.domain.accompany

import org.springframework.data.jpa.repository.JpaRepository

interface AccompanyTagRepository : JpaRepository<AccompanyTagEntity, AccompanyTagPk> {

    fun findByAccompanyId(accompanyId : Long) : List<AccompanyTagEntity>?

}