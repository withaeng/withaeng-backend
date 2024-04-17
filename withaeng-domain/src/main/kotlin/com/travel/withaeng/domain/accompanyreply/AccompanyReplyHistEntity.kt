package com.travel.withaeng.domain.accompanyreply

import com.travel.withaeng.domain.AccompanyBaseEntity
import jakarta.persistence.*

@Table(name = "accompany_reply_hist")
@Entity
class AccompanyReplyHistEntity (

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "hist_id", nullable = false)
   val histId: Long,

   @Column(name = "reply_id", nullable = false)
   val replyId: Long,

    @Column(name = "accompany_id", nullable = false)
    val accompanyId : Long,

    @Column(name = "parent_id", nullable = false)
    val parentId : Long,

    @Column(name = "depth", nullable = false)
    val depth : Long,

    @Column(name = "reply_order", nullable = false)
    val replyOrder : Long,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "content", nullable = false)
    val content: String,

   @Column(name = "exec_cd", nullable = false)
   val execCd: String,

) : AccompanyBaseEntity()