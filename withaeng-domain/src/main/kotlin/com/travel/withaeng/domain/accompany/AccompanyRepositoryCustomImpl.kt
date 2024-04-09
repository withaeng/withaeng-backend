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
            //.where(listWhere(param, QAccompanyEntity.accompanyEntity))
            .orderBy(listOrder(param))
            .offset(1)
            .limit(param.pageSize)
            .fetch()
    }

    fun listWhere(param : SearchAccompanyDTO, accompanyEntity : QAccompanyEntity, ) : BooleanExpression {

        var predicate: BooleanExpression = Expressions.asBoolean(true).isTrue

        if (param.startTripDate != null && param.endTripDate != null) {
            predicate = predicate
                .and(accompanyEntity.startTripDate.between(param.startTripDate, param.endTripDate)
                .or(accompanyEntity.endTripDate.between(param.startTripDate, param.endTripDate)))
        }

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