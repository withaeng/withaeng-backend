package com.travel.withaeng.domain.accompanyreply

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.travel.withaeng.domain.accompany.*
import org.springframework.stereotype.Repository

@Repository
class AccompanyReplyRepositoryCustomImpl (val jpaQueryFactory: JPAQueryFactory) : AccompanyReplyRepositoryCustom {
    override fun getAccompanyReplyList(accompanyId: Long): List<GetReplyDTO> {
        return jpaQueryFactory
            .select(
                Projections.constructor(
                    GetReplyDTO::class.java,
                QAccompanyReplyEntity.accompanyReplyEntity.replyId,
                QAccompanyReplyEntity.accompanyReplyEntity.userId,
                QAccompanyReplyEntity.accompanyReplyEntity.accompanyId,
                QAccompanyReplyEntity.accompanyReplyEntity.parentId,
                QAccompanyReplyEntity.accompanyReplyEntity.depth,
                QAccompanyReplyEntity.accompanyReplyEntity.content,
            ))
            .from(QAccompanyReplyEntity.accompanyReplyEntity)
            .where(QAccompanyReplyEntity.accompanyReplyEntity.accompanyId.eq(accompanyId))
            .orderBy(QAccompanyReplyEntity.accompanyReplyEntity.parentId.desc(), QAccompanyReplyEntity.accompanyReplyEntity.depth.desc())
            .fetch()
    }
}