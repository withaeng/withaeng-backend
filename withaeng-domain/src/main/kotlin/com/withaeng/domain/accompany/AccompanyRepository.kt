package com.withaeng.domain.accompany

import org.springframework.data.jpa.repository.JpaRepository

interface AccompanyRepository : JpaRepository<Accompany, Long>, AccompanyRepositoryCustom {

    fun countByUserId(userId: Long): Int
}