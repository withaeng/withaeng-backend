package com.travel.withaeng.domain.accompanylike

import com.travel.withaeng.domain.AccompanyBaseEntity
import jakarta.persistence.*

@Table(name = "accompany_like")
@Entity
class AccompanyLikeEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id", nullable = false)
    val likeId: Long,

    @Column(name = "accompany_id", nullable = false)
    val accompanyId: Long,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

) : AccompanyBaseEntity()