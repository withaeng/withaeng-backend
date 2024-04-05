package com.travel.withaeng.domain.accompany

import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Order
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class AccompanyRepositoryCustomImpl (val jpaQueryFactory: JPAQueryFactory) : AccompanyRepositoryCustom {

    override fun getAccompany(accompanyId : Long) : GetDTO? {
        return jpaQueryFactory
            .select(Projections.constructor(GetDTO::class.java,
                QAccompanyEntity.accompanyEntity.accompanyId,
                QAccompanyEntity.accompanyEntity.userId,
                QAccompanyEntity.accompanyEntity.title,
                QAccompanyEntity.accompanyEntity.content,
                QAccompanyDestinationEntity.accompanyDestinationEntity.continent,
                QAccompanyDestinationEntity.accompanyDestinationEntity.country,
                QAccompanyDestinationEntity.accompanyDestinationEntity.city,
                QAccompanyEntity.accompanyEntity.startTripDate,
                QAccompanyEntity.accompanyEntity.endTripDate,
                QAccompanyEntity.accompanyEntity.bannerImageUrl,
                QAccompanyEntity.accompanyEntity.accompanyCnt,
                QAccompanyDetailEntity.accompanyDetailEntity.viewCnt,
                QAccompanyDetailEntity.accompanyDetailEntity.likeCnt,
                ))
            .from(QAccompanyEntity.accompanyEntity)
                .innerJoin(QAccompanyDestinationEntity.accompanyDestinationEntity)
                    .on(QAccompanyDestinationEntity.accompanyDestinationEntity.accompanyId.eq(QAccompanyEntity.accompanyEntity.accompanyId))
                .innerJoin(QAccompanyDetailEntity.accompanyDetailEntity)
                    .on(QAccompanyDetailEntity.accompanyDetailEntity.accompanyId.eq(QAccompanyEntity.accompanyEntity.accompanyId))
            .where(QAccompanyEntity.accompanyEntity.accompanyId.eq(accompanyId))
            .fetchOne()
    }

    override fun getAccompanyList(param : SearchAccompanyDTO) : List<GetDTO>{

        return jpaQueryFactory
            .select(Projections.constructor(GetDTO::class.java,
                QAccompanyEntity.accompanyEntity.accompanyId,
                QAccompanyEntity.accompanyEntity.userId,
                QAccompanyEntity.accompanyEntity.title,
                QAccompanyEntity.accompanyEntity.content,
                QAccompanyDestinationEntity.accompanyDestinationEntity.continent,
                QAccompanyDestinationEntity.accompanyDestinationEntity.country,
                QAccompanyDestinationEntity.accompanyDestinationEntity.city,
                QAccompanyEntity.accompanyEntity.startTripDate,
                QAccompanyEntity.accompanyEntity.endTripDate,
                QAccompanyEntity.accompanyEntity.bannerImageUrl,
                QAccompanyEntity.accompanyEntity.accompanyCnt,
                QAccompanyDetailEntity.accompanyDetailEntity.viewCnt,
                QAccompanyDetailEntity.accompanyDetailEntity.likeCnt,
            ))
            .from(QAccompanyEntity.accompanyEntity)
                .innerJoin(QAccompanyDestinationEntity.accompanyDestinationEntity)
                    .on(QAccompanyDestinationEntity.accompanyDestinationEntity.accompanyId.eq(QAccompanyEntity.accompanyEntity.accompanyId))
                .innerJoin(QAccompanyDetailEntity.accompanyDetailEntity)
                    .on(QAccompanyDetailEntity.accompanyDetailEntity.accompanyId.eq(QAccompanyEntity.accompanyEntity.accompanyId))
            .where(QAccompanyEntity.accompanyEntity.startTripDate.after(param.startTripDate), QAccompanyEntity.accompanyEntity.endTripDate.before(param.endTripDate))
            .orderBy(QAccompanyEntity.accompanyEntity.updatedAt.desc())
            .offset(param.getCurrentPage())
            .limit(param.pageSize)
            .fetch()
    }

}