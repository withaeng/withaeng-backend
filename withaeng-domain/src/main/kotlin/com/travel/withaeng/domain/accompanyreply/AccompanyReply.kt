package com.travel.withaeng.domain.accompanyreply

import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate

@DynamicUpdate
@Table(name = "accompany_reply")
@Entity
class AccompanyReply(

    @Column(name = "accompany_id", nullable = false)
    val accompanyId: Long,

    @Column(name = "parent_id", nullable = false)
    val parentId: Long,

    @Column(name = "depth", nullable = false)
    val depth: Long,

    @Column(name = "reply_order", nullable = false)
    val replyOrder: Long,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "content", nullable = false)
    var content: String

) : BaseEntity()