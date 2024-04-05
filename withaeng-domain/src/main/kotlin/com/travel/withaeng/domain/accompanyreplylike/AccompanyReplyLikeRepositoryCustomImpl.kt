package com.travel.withaeng.domain.accompanyreplylike

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class AccompanyReplyLikeRepositoryCustomImpl(val jpaQueryFactory: JPAQueryFactory) : AccompanyReplyLikeRepositoryCustom {
    override fun getAccompanyReplyLikeList(replyIds: List<Long>): List<GetReplyLikeDTO> {
        return jpaQueryFactory
            .select(
                Projections.constructor(
                    GetReplyLikeDTO::class.java,
                    QAccompanyReplyLikeEntity.accompanyReplyLikeEntity.replyId,
                    QAccompanyReplyLikeEntity.accompanyReplyLikeEntity.replyId.count()
                ))
            .from(QAccompanyReplyLikeEntity.accompanyReplyLikeEntity)
            .where(QAccompanyReplyLikeEntity.accompanyReplyLikeEntity.replyId.`in`(replyIds))
            .groupBy(QAccompanyReplyLikeEntity.accompanyReplyLikeEntity.replyId)
            .fetch()
    }
}