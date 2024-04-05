package com.travel.withaeng.domain.accompany

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface AccompanyRepository : JpaRepository<AccompanyEntity, Long>, AccompanyRepositoryCustom {

    fun findByAccompanyId(accompanyId : Long) : AccompanyEntity?

}