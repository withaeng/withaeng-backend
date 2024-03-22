package com.travel.withaeng.domain.accompany

import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.*
import java.time.LocalDate

@Table(name = "accompany_hist")
@Entity
class AccompanyHistEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hist_id", nullable = false)
    val histId: Long,

    @Column(name = "accompany_id", nullable = false)
    val accompanyId: Long,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "title", nullable = false)
    var title: String,

    @Lob
    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "accompany_status_cd", nullable = false)
    var accompanyStatusCd : String,

    @Column(name = "start_trip_date", nullable = false)
    var startTripDate: LocalDate,

    @Column(name = "end_trip_date", nullable = false)
    var endTripDate: LocalDate,

    @Column(name = "banner_image_url")
    var bannerImageUrl: String?,

    @Column(name = "accompany_cnt", nullable = false)
    var accompanyCnt: Long = 0L,

    @Column(name = "view_cnt", nullable = false)
    var viewCnt: Long = 0L

) : BaseEntity()