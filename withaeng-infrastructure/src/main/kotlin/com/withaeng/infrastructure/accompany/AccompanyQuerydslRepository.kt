package com.withaeng.infrastructure.accompany

import com.withaeng.domain.accompany.FindAccompanyDto

interface AccompanyQuerydslRepository {
    fun findAccompanyDetail(accompanyId: Long): FindAccompanyDto?
}