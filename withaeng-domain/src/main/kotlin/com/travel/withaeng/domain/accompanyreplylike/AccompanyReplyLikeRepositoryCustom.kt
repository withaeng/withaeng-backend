package com.travel.withaeng.domain.accompanyreplylike

import com.travel.withaeng.domain.accompanyreply.GetReplyDTO

interface AccompanyReplyLikeRepositoryCustom {

    fun getAccompanyReplyLikeList(replyIds : List<Long>) : List<GetReplyLikeDTO>

}