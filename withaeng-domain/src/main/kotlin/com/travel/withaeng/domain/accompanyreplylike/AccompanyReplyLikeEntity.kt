package com.travel.withaeng.domain.accompanyreplylike

import com.travel.withaeng.domain.AccompanyBaseEntity
import jakarta.persistence.*

@Table(name = "accompany_reply_like")
@Entity
class AccompanyReplyLikeEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_like_id", nullable = false)
    val replyLikeId: Long,

    @Column(name = "reply_id", nullable = false)
    val replyId: Long,

    @Column(name = "user_id", nullable = false)
    val userId: Long

) : AccompanyBaseEntity()