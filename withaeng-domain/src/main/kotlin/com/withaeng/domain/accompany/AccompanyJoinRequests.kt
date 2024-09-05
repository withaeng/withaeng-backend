package com.withaeng.domain.accompany

import jakarta.persistence.CascadeType
import jakarta.persistence.Embeddable
import jakarta.persistence.OneToMany

@Embeddable
class AccompanyJoinRequests(

    @OneToMany(mappedBy = "accompany", cascade = [CascadeType.ALL], orphanRemoval = true)
    var joinRequests: MutableList<AccompanyJoinRequest> = mutableListOf(),

    ) {

    fun requestJoin(userId: Long, accompany: Accompany) {
        if (canRequest(userId)) {
            throw IllegalArgumentException("차단된 사용자입니다.")
        }
        this.joinRequests.add(
            AccompanyJoinRequest.create(
                userId = userId,
                accompany = accompany,
            )
        )
    }

    fun acceptJoin(hostId: Long, joinRequestId: Long) {
        findById(joinRequestId).accept()
    }

    fun acceptedCount(): Int {
        return this.joinRequests.count { it.onStatus(AccompanyJoinRequestStatus.ACCEPT) }
    }

    private fun canRequest(userId: Long) = this.joinRequests.none { it.duplicated(userId) }

    private fun findById(joinRequestId: Long) = (this.joinRequests.find { it.id == joinRequestId }
        ?: throw IllegalArgumentException("동행 참가 요청을 찾을 수 없습니다."))
}
