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
    val title: String,

    @Lob
    @Column(name = "content", nullable = false)
    val content: String,

    @Column(name = "accompany_status_cd", nullable = false)
    val accompanyStatusCd : String,

    @Embedded
    val destination: Destination,

    @Column(name = "start_trip_date", nullable = false)
    val startTripDate: LocalDate,

    @Column(name = "end_trip_date", nullable = false)
    val endTripDate: LocalDate,

    @Column(name = "banner_image_url")
    val bannerImageUrl: String?,

    @Column(name = "accompany_cnt", nullable = false)
    val accompanyCnt: Long = 0L

) : BaseEntity()