package com.travel.withaeng.domain.accompany

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class AccompanyRepositoryCustomImpl (val jpaQueryFactory: JPAQueryFactory) : AccompanyRepositoryCustom {

    override fun getAccompanyList(param : SearchAccompanyDTO): List<ReadAccompanyDTO>? {

    }
}