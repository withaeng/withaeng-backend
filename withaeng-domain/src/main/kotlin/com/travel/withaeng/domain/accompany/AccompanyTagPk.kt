package com.travel.withaeng.domain.accompany

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class AccompanyTagPk (

    @Column(name = "accompany_id", nullable = false)
    val accompanyId : Long,

    @Column(name = "tag_nm", nullable = false)
    val tagNm : String

) : Serializable