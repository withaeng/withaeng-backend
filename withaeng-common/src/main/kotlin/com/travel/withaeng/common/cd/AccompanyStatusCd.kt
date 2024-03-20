package com.travel.withaeng.common.cd

enum class AccompanyStatusCd (

    val statusCd : String,
    val statusNm : String

) {

    ING("ING", "동행 구인중"),
    COMPLETE("COMPLETE", "동행 모집 완료"),
}
