package com.travel.withaeng.domain.accompany

import org.springframework.data.jpa.repository.JpaRepository

interface AccompanyRepository : JpaRepository<AccompanyEntity, Long> {

    fun findByAccompanyId(accompanyId : Long) : AccompanyEntity?

    fun getAccompanyList() : List<AccompanyEntity>?
}