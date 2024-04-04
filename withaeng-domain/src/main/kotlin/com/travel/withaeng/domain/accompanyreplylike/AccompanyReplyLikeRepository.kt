package com.travel.withaeng.domain.accompanyreplylike

import org.springframework.data.jpa.repository.JpaRepository

interface AccompanyReplyLikeRepository : JpaRepository<AccompanyReplyLikeEntity, Long>{

    fun countByReplyId(replyId : Long) : Long
}