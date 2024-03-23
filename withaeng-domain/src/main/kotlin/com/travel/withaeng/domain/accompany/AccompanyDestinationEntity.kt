package com.travel.withaeng.domain.accompany

import com.travel.withaeng.domain.AccompanyBaseEntity
import jakarta.persistence.*

@Table(name = "accompany_destinaion")
@Entity
class AccompanyDestinationEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id", nullable = false)
    val destinationId: Long,

    @Column(name = "accompany_id", nullable = false)
    val accompanyId: Long,

    @Column(name = "continent", nullable = false, updatable = true)
    var continent: String,

    @Column(name = "country", nullable = true, updatable = true)
    var country : String?,

    @Column(name = "city", nullable = true, updatable = true)
    var city : String?,

) : AccompanyBaseEntity()
