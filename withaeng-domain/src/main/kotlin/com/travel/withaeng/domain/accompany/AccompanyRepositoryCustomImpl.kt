package com.travel.withaeng.domain.accompany

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class AccompanyRepositoryCustomImpl (val jpaQueryFactory: JPAQueryFactory) : AccompanyRepositoryCustom {

}