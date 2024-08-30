package com.withaeng.infrastructure.accompany

import com.withaeng.common.exception.WithaengException
import com.withaeng.common.exception.WithaengExceptionType
import com.withaeng.domain.accompany.Accompany
import com.withaeng.domain.accompany.AccompanyReader
import com.withaeng.domain.accompany.dto.FindAccompanyDetailResult
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class AccompanyReaderImpl(
    private val accompanyJpaRepository: AccompanyJpaRepository,
    private val accompanyQuerydslRepository: AccompanyQuerydslRepository,
) : AccompanyReader {

    override fun findAccompanyDetail(accompanyId: Long): FindAccompanyDetailResult {
        val findAccompanyDto = (accompanyQuerydslRepository.findAccompanyDetail(accompanyId)
            ?: throw WithaengException.of(
                type = WithaengExceptionType.NOT_EXIST // TODO
            ))
        return findAccompanyDto.toResult()
    }

    override fun findByIdOrNull(accompanyId: Long): Accompany {
        return accompanyJpaRepository.findByIdOrNull(accompanyId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
        )
    }

    override fun findAll(): List<Accompany> {
        return accompanyJpaRepository.findAll()
    }

}