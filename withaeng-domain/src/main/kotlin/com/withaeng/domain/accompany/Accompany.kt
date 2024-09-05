package com.withaeng.domain.accompany

import com.withaeng.domain.BaseEntity
import com.withaeng.domain.accompanystatistics.AccompanyStatistics
import com.withaeng.domain.converter.TagIdsConverter
import com.withaeng.domain.user.UserPreferAccompanyGender
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate

@Entity
@DynamicUpdate
@Table(name = "accompany")
class Accompany(

    @Column(name = "host_id", nullable = false)
    val hostId: Long,

    @Column(name = "title", nullable = false)
    @Comment("동행 제목")
    var title: String,

    @Lob
    @Column(name = "content", nullable = false)
    @Comment("동행 내용")
    var content: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "accompany_status", nullable = false)
    @Comment("동행 모집 상태")
    var accompanyStatus: AccompanyStatus = AccompanyStatus.ING,

    @Column(name = "start_trip_date", nullable = false)
    @Comment("여행 시작 일자")
    var startTripDate: LocalDate,

    @Column(name = "end_trip_date", nullable = false)
    @Comment("여행 종료 일자")
    var endTripDate: LocalDate,

    @Column(name = "banner_image_url")
    @Comment("베너 이미지 URI")
    var bannerImageUrl: String?,

    @Column(name = "member_count", nullable = false)
    @Comment("모집 인원")
    var memberCount: Long = 0L,

    @Column(name = "open_kakao_url", nullable = false)
    @Comment("오픈 카카오 채팅 URI")
    var openKakaoUrl: String,

    @Embedded
    @Comment("동행 장소 정보")
    var accompanyDestination: AccompanyDestination,

    @Column(name = "start_accompany_age", nullable = false)
    @Comment("동행 시작 연령")
    var startAccompanyAge: Int,

    @Column(name = "end_accompany_age", nullable = false)
    @Comment("동행 종료 연령")
    var endAccompanyAge: Int,

    @Column(name = "prefer_gender", nullable = false)
    @Comment("동행 선호 성별")
    var preferGender: UserPreferAccompanyGender,

    @Convert(converter = TagIdsConverter::class)
    @Column(name = "tag_ids", nullable = false)
    @Comment("태그 목록")
    var tagIds: Set<Long> = setOf(),

    @Embedded
    @Comment("동행 가입 신청 목록")
    var joinRequests: AccompanyJoinRequests = AccompanyJoinRequests(),

    @OneToOne(mappedBy = "accompany", cascade = [CascadeType.ALL], orphanRemoval = true)
    var accompanyStatistics: AccompanyStatistics? = null,

    ) : BaseEntity() {

    // ##############################################################################################################
    // 동행 참여 요청 - 게스트
    fun requestJoin(userId: Long) {
        if (this.isHost(userId)) {
            throw IllegalArgumentException("This User is Host")
        }
        validIsNotFull()
        this.joinRequests.requestJoin(userId, this)
    }

    // 동행 참여 승인 - 호스트
    fun acceptJoin(hostId: Long, joinRequestId: Long) {
        if (!this.isHost(hostId)) {
            throw IllegalArgumentException("This User is not Host")
        }
        validIsNotFull()
        this.joinRequests.acceptJoin(hostId, joinRequestId)
        if (this.isFull()) {
            this.accompanyStatus = AccompanyStatus.COMPLETE
        }
    }

    private fun isHost(userId: Long): Boolean {
        return this.hostId == userId
    }

    private fun isFull(): Boolean {
        return this.accompanyStatus == AccompanyStatus.COMPLETE ||
                this.memberCount <= this.joinRequests.acceptedCount() + 1
    }

    private fun validIsNotFull() {
        if (this.isFull()) {
            throw IllegalArgumentException("This Accompany is Full")
        }
    }

    // ##############################################################################################################

    fun increaseViewCount() {
        this.accompanyStatistics?.increaseViewCount()
    }

    fun update(
        content: String?,
        tagIds: Set<Long>?,
    ) {
        this.content = content ?: this.content
        this.tagIds = tagIds ?: this.tagIds
    }

    companion object {
        fun create(params: CreateAccompanyDto): Accompany {
            val accompany = Accompany(
                hostId = params.userId,
                title = params.title,
                content = params.content,
                startTripDate = params.startTripDate,
                endTripDate = params.endTripDate,
                bannerImageUrl = params.bannerImageUrl,
                memberCount = params.memberCount,
                openKakaoUrl = params.openKakaoUrl,
                accompanyDestination = params.destination,
                startAccompanyAge = params.startAccompanyAge.value,
                endAccompanyAge = params.endAccompanyAge.value,
                preferGender = params.preferGender,
                tagIds = params.tagIds ?: setOf(),
            )
            accompany.accompanyStatistics = AccompanyStatistics(accompany = accompany)
            return accompany
        }
    }
}