package com.withaeng.domain.accompany

import com.withaeng.domain.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "accompany_join_request")
class AccompanyJoinRequest private constructor(

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Enumerated(EnumType.STRING)
    var status: AccompanyJoinRequestStatus,

    @ManyToOne
    @JoinColumn(name = "accompany_id")
    val accompany: Accompany,

    ) : BaseEntity() {

    companion object {
        fun create(userId: Long, accompany: Accompany): AccompanyJoinRequest {
            return AccompanyJoinRequest(
                userId = userId,
                status = AccompanyJoinRequestStatus.WAIT,
                accompany = accompany,
            )
        }
    }

    fun accept() {
        status = AccompanyJoinRequestStatus.ACCEPT
    }

    fun duplicated(userId: Long): Boolean {
        return requestedBy(userId) && (onStatus(AccompanyJoinRequestStatus.WAIT) || onStatus(AccompanyJoinRequestStatus.ACCEPT))
    }

    fun requestedBy(userId: Long): Boolean {
        return this.userId == userId
    }

    fun onStatus(accept: AccompanyJoinRequestStatus): Boolean {
        return this.status == accept
    }
}