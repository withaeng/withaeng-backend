package com.withaeng.domain.accompany

import com.withaeng.domain.accompany.dto.FindAccompanyDetailResult

interface AccompanyReader {
    fun findAccompanyDetail(accompanyId: Long): FindAccompanyDetailResult
    fun findByIdOrNull(accompanyId: Long): Accompany
    fun findAll(): List<Accompany>
}