package com.travel.withaeng.domain.accompany

import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface AccompanyRepositoryCustom {

    fun getAccompany(accompanyId : Long) : GetDTO?

    fun getAccompanyList(param : SearchAccompanyDTO) : List<GetDTO>
}