package com.travel.withaeng.common.cd

enum class ExecCd (

        val execCd : String,
        val execNm : String

) {

    CREATE("CREATE", "등록"),
    UPDATE("UPDATE", "수정"),
    DELETE("DELETE", "삭제")

}