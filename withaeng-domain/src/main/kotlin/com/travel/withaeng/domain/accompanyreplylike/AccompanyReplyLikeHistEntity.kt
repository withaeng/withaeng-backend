package com.travel.withaeng.domain.accompanyreplylike

import com.travel.withaeng.common.cd.ExecCd
import com.travel.withaeng.domain.AccompanyBaseEntity
import jakarta.persistence.*

@Table(name = "accompany_reply_like_hist")
@Entity
class AccompanyReplyLikeHistEntity (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "hist_id", nullable = false)
        val histId: Long,

        @Column(name = "reply_like_id", nullable = false)
        val replyLikeId: Long,

        @Column(name = "reply_id", nullable = false)
        val replyId: Long,

        @Column(name = "user_id", nullable = false)
        val userId: Long,

        @Column(name = "exec_cd", nullable = false)
        val execCd : String

) : AccompanyBaseEntity()