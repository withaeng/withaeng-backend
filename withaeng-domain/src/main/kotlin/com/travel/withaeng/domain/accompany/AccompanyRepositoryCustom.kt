package com.travel.withaeng.domain.accompany

interface AccompanyRepositoryCustom {
    fun getAccompanyList(param : SearchAccompanyDTO) : List<ReadAccompanyDTO>?

}