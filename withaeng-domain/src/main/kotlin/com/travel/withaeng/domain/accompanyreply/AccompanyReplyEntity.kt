package com.travel.withaeng.domain.accompanyreply

import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.*

@Table(name = "accompany_reply")
@Entity
class AccompanyReplyEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id", nullable = false)
    val replyId: Long,

    @Column(name = "accompany_id", nullable = false)
    val accompanyId : Long,

    @Column(name = "parent_id", nullable = false)
    val parentId : Long,

    @Column(name = "depth", nullable = false)
    val depth : Long,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "content", nullable = false)
    val content: String,

) : BaseEntity()