package com.travel.withaeng.domain.accompany

import com.fasterxml.jackson.annotation.JsonProperty
import com.travel.withaeng.common.cd.AccompanyStatusCd
import com.travel.withaeng.common.cd.ExecCd
import jakarta.validation.constraints.NotBlank
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.jetbrains.annotations.NotNull
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate

class AccompanyDto ()

@Setter
@Getter
class CreateAccompanyDTO(

    @NotNull
    @JsonProperty("userId")
    val userId: Long,

    @NotBlank(message = "제목은 필수 값 입니다.")
    @JsonProperty("title")
    val title: String,

    @NotBlank(message = "내용은 필수 값 입니다.")
    @JsonProperty("content")
    val content: String,

    @NotBlank(message = "대륙은 필수 값 입니다.")
    val continent: String,

    val country : String? = null,

    val city : String? = null,

    @NotBlank
    val startTripDate: LocalDate,

    @NotBlank
    val endTripDate: LocalDate,

    val bannerImageUrl: String? = null,

    @field:NotNull
    val accompanyCnt : Long,

    val tags : List<String>?,

    @NotBlank(message = "카카오 오픈 채팅 URL은 필수 값 입니다.")
    val openKakaoUrl : String

){
    fun toEntity(): AccompanyEntity {
        return AccompanyEntity(
            0,
            this.userId,
            this.title,
            this.content,
            AccompanyStatusCd.ING.statusCd,
            this.startTripDate,
            this.endTripDate,
            this.bannerImageUrl,
            this.accompanyCnt
        )
    }

    fun toHistEntity(entity: AccompanyEntity): AccompanyHistEntity {
        return AccompanyHistEntity(
            0,
            entity.accompanyId,
            entity.userId,
            entity.title,
            entity.content,
            entity.accompanyStatusCd,
            entity.startTripDate,
            entity.endTripDate,
            entity.bannerImageUrl,
            entity.accompanyCnt,
            ExecCd.CREATE.execCd
        )
    }

    fun toDestinationEntity(accompanyId : Long) : AccompanyDestinationEntity {
        return AccompanyDestinationEntity(
                0,
                accompanyId,
                this.continent,
                this.country,
                this.city
            )
    }

    fun toDetailEntity(accompanyId : Long) : AccompanyDetailEntity {
        return AccompanyDetailEntity(
            accompanyId,
            0,
            0,
            this.openKakaoUrl
        )
    }

    fun toTagEntity(accompanyId : Long) : List<AccompanyTagEntity>? {

        if(tags == null){
            return null
        }

        val tagList = mutableListOf<AccompanyTagEntity>()
        for(tagNm in tags){
            tagList.add(AccompanyTagEntity(0, accompanyId, tagNm))
        }

        return tagList
    }
}

@Setter
@Getter
class ModifyAccompanyDTO(

    @NotNull
    val accompanyId : Long,

    @NotNull
    val userId: Long,

    @NotBlank(message = "제목은 필수 값 입니다.")
    val title: String,

    @NotBlank(message = "내용은 필수 값 입니다.")
    val content: String,

    @NotBlank(message = "대륙은 필수 값 입니다.")
    val continent: String,

    val country : String? = null,

    val city : String? = null,

    @NotBlank
    val startTripDate: LocalDate,

    @NotBlank
    val endTripDate: LocalDate,

    val bannerImageUrl: String? = null,

    @NotNull
    val accompanyCnt : Long,

    val tags : List<String>?,

    @NotBlank(message = "카카오 오픈 채팅 URL은 필수 값 입니다.")
    val openKakaoUrl : String

){
    fun toTagEntity(accompanyId : Long) : List<AccompanyTagEntity>? {

        if(tags == null){
            return null
        }

        val tagList = mutableListOf<AccompanyTagEntity>()
        for(tagNm in tags){
            tagList.add(AccompanyTagEntity(0, accompanyId, tagNm))
        }

        return tagList
    }
}

data class GetDTO (

    var accompanyId : Long,
    var userId: Long,
    var title: String,
    var content: String,
    var continent: String,
    var country : String? = null,
    var city : String? = null,
    var startTripDate: LocalDate,
    var endTripDate: LocalDate,
    var bannerImageUrl: String? = null,
    var accompanyCnt : Long,
    var viewCnt : Long,
    var likeCnt : Long,
    var tags : List<String>? = null,
    var openKakaoUrl : String

)

data class SearchAccompanyDTO(

    var viewCntOrder: Boolean,//조회수 높은 순서

    var likeCntOrder: Boolean,//좋아요 높은 순서

    var startTripDate: LocalDate,//동행 모집 시작일시

    var endTripDate: LocalDate,//동행 모집 마감일시

    var pageIndex : Long,

    var pageSize : Long

){
    fun getCurrentPage() : Long{
        return pageIndex * pageSize
    }

}