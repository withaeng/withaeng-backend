package com.travel.withaeng.domain.accompanylike

import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.*

@Table(name = "accompany_like_hist")
@Entity
class AccompanyLikeHistEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hist_id", nullable = false)
    val histId: Long,

    @Column(name = "like_id", nullable = false)
    val likeId: Long,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "accompany_id", nullable = false)
    val accompanyId: Long

) : BaseEntity()