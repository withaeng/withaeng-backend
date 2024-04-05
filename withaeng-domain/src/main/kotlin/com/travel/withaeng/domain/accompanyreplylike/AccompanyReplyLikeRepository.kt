package com.travel.withaeng.domain.accompanyreplylike

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AccompanyReplyLikeRepository : JpaRepository<AccompanyReplyLikeEntity, Long>, AccompanyReplyLikeRepositoryCustom{

    fun countByReplyId(replyId: Long) : Long

}