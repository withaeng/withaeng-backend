package com.travel.withaeng.domain.accompany

import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.Entity
import org.springframework.stereotype.Repository
import java.time.LocalTime

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
                QAccompanyDetailEntity.accompanyDetailEntity.openKakaoUrl
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
                QAccompanyDetailEntity.accompanyDetailEntity.openKakaoUrl
            ))
            .from(QAccompanyEntity.accompanyEntity)
                .innerJoin(QAccompanyDestinationEntity.accompanyDestinationEntity)
                    .on(QAccompanyDestinationEntity.accompanyDestinationEntity.accompanyId.eq(QAccompanyEntity.accompanyEntity.accompanyId))
                .innerJoin(QAccompanyDetailEntity.accompanyDetailEntity)
                    .on(QAccompanyDetailEntity.accompanyDetailEntity.accompanyId.eq(QAccompanyEntity.accompanyEntity.accompanyId))
            .where(listWhere(param, QAccompanyEntity.accompanyEntity))
            .orderBy(listOrder(param))
            .offset(param.pageIndex)
            .limit(param.pageSize)
            .fetch()
    }

    fun listWhere(param : SearchAccompanyDTO, accompanyEntity : QAccompanyEntity) : BooleanExpression {

        var predicate: BooleanExpression = Expressions.asBoolean(true).isTrue
        val searchStartDate = param.startTripDate.atTime(LocalTime.MIN)
        val searchEndDate = param.endTripDate.atTime(LocalTime.MAX)

        predicate = predicate
            .and(
                    accompanyEntity.startTripDate.between(searchStartDate, searchEndDate)
                    .or(
                    accompanyEntity.endTripDate.between(searchStartDate, searchEndDate)
                    )
                    .or(Expressions.allOf(accompanyEntity.startTripDate.before(searchStartDate), accompanyEntity.endTripDate.after(searchEndDate)))
            )

        return predicate
    }

    fun listOrder(param : SearchAccompanyDTO) : OrderSpecifier<*> {
        if (param.likeCntOrder) {
            return OrderSpecifier(Order.DESC, QAccompanyDetailEntity.accompanyDetailEntity.likeCnt)
        } else if(param.viewCntOrder) {
            return OrderSpecifier(Order.DESC, QAccompanyDetailEntity.accompanyDetailEntity.viewCnt)
        }else {
            return OrderSpecifier(Order.DESC, QAccompanyEntity.accompanyEntity.updatedAt)
        }
    }
}