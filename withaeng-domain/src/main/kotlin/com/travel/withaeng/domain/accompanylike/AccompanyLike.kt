package com.travel.withaeng.domain.accompanylike

import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Table(name = "accompany_like")
@Entity
class AccompanyLike(
    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "accompany_id", nullable = false)
    val accompanyId: Long
) : BaseEntity()