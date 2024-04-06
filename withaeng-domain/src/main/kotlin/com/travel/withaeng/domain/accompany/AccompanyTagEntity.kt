package com.travel.withaeng.domain.accompany

import com.travel.withaeng.domain.AccompanyBaseEntity
import jakarta.persistence.*

@Table(name = "accompany_tag")
@Entity
class AccompanyTagEntity (

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "tag_id", nullable = false)
     val tagId : Long,

     @Column(name = "accompany_id", nullable = false)
     val accompanyId : Long,

     @Column(name = "tag_nm", nullable = false)
     val tagNm : String

) : AccompanyBaseEntity()