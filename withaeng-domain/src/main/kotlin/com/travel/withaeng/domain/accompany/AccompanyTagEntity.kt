package com.travel.withaeng.domain.accompany

import jakarta.persistence.*

@Table(name = "accompany_tag")
@Entity
class AccompanyTagEntity (

    @EmbeddedId
    val accompanyTagPk: AccompanyTagPk,

)