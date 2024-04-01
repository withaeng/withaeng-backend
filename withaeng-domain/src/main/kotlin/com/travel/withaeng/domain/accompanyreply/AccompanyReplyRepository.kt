package com.travel.withaeng.domain.accompanyreply

import org.springframework.data.jpa.repository.JpaRepository

interface AccompanyReplyRepository : JpaRepository<AccompanyReplyEntity, Long> {
    fun findByReplyId(replyId : Long) : AccompanyReplyEntity?

    fun findByAccompanyId(accompanyId : Long) : List<AccompanyReplyEntity>?

}