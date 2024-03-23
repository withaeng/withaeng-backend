package com.travel.withaeng.domain.accompany

import com.travel.withaeng.domain.AccompanyBaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate

@DynamicUpdate
@Table(name = "accompany")
@Entity
class AccompanyEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

) : AccompanyBaseEntity()