package com.travel.withaeng.domain.accompanyreply

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface AccompanyReplyRepository : JpaRepository<AccompanyReplyEntity, Long>, AccompanyReplyRepositoryCustom {
    fun findByReplyId(replyId : Long) : AccompanyReplyEntity?

}