package com.withaeng.infrastructure.accompany

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.withaeng.domain.accompany.FindAccompanyDto
import com.withaeng.domain.accompany.QAccompany.accompany
import com.withaeng.domain.accompany.QFindAccompanyDto
import com.withaeng.domain.accompany.QFindAccompanyUserInfoDto
import com.withaeng.domain.accompanylike.QAccompanyLike.accompanyLike
import com.withaeng.domain.accompanystatistics.QAccompanyStatistics.accompanyStatistics
import com.withaeng.domain.user.QUser.user
import org.springframework.stereotype.Component

@Component
class AccompanyRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : AccompanyQuerydslRepository {

    override fun findAccompanyDetail(accompanyId: Long): FindAccompanyDto? {
        return queryFactory
            .select(
                QFindAccompanyDto(
                    accompany.id,
                    accompany.userId,
                    accompany.title,
                    accompany.content,
                    accompany.accompanyDestination,
                    accompany.startTripDate,
                    accompany.endTripDate,
                    accompany.bannerImageUrl,
                    accompany.memberCount,
                    accompanyStatistics.viewCount,
                    accompany.openKakaoUrl,
                    accompany.startAccompanyAge,
                    accompany.endAccompanyAge,
                    accompany.preferGender,
                    accompany.tagIds,
                    accompanyLike.count(),
                    QFindAccompanyUserInfoDto(
                        user.nickname,
                        user.profileImageUrl,
                        user.isMale,
                        user.bio,
                        user.createdAt,
                    ),
                )
            )
            .from(accompany)
            .leftJoin(accompanyLike)
            .on(accompany.id.eq(accompanyLike.accompanyId))
            .innerJoin(accompanyStatistics)
            .on(accompany.id.eq(accompanyStatistics.accompany.id))
            .innerJoin(user)
            .on(accompany.userId.eq(user.id))
            .where(
                accompanyIdEq(accompanyId)
            )
            .groupBy(accompanyLike.accompanyId)
            .fetchOne()
    }

    private fun accompanyIdEq(accompanyId: Long): BooleanExpression {
        return accompany.id.eq(accompanyId)
    }
}
