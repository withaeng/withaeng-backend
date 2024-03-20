package com.travel.withaeng.domain.accompany

import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.*

@Table(name = "accompany_category")
@Entity
class AccompanyCategoryEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    val categoryId: Long,

    @Column(name = "accompany_id", nullable = false)
    val accompanyId: Long,

    @Column(name = "continent", nullable = false)
    val continent: String,

    @Column(name = "country", nullable = false)
    val country : String,

    @Column(name = "city", nullable = false)
    val city : String,

) : BaseEntity()
