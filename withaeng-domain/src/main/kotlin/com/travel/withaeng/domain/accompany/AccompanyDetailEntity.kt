package com.travel.withaeng.domain.accompany

import com.travel.withaeng.domain.AccompanyBaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@DynamicUpdate
@Table(name = "accompany_detail")
@Entity
class AccompanyDetailEntity (

    @Id
    @Column(name = "accompany_id", nullable = false)
    val accompanyId: Long,

    @Column(name = "view_cnt", nullable = false)
    var viewCnt: Long = 0L,

    @Column(name = "like_cnt", nullable = false)
    var likeCnt: Long = 0L,

    @Column(name = "open_kakao_url", nullable = false)
    var openKakaoUrl : String


) : AccompanyBaseEntity()