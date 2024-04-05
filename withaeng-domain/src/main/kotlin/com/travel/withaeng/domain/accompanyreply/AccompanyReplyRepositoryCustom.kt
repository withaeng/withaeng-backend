package com.travel.withaeng.domain.accompanyreply

import org.springframework.data.domain.Sort

interface AccompanyReplyRepositoryCustom {

    fun getAccompanyReplyList(accompanyId : Long) : List<GetReplyDTO>?

}