package com.travel.withaeng.domain.accompany

import com.travel.withaeng.common.cd.AccompanyStatusCd
import jakarta.validation.constraints.NotBlank
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.jetbrains.annotations.NotNull
import java.time.LocalDate

class AccompanyDto ()

@Setter
@Getter
class CreateAccompanyDTO(

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

    val tags : List<String>?

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
            entity.accompanyCnt
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
            0
        )
    }

    fun toTagEntity(accompanyId : Long) : List<AccompanyTagEntity>? {

        if(tags == null){
            return null
        }

        val tagList = mutableListOf<AccompanyTagEntity>()
        for(tagNm in tags){
            tagList.add(AccompanyTagEntity(AccompanyTagPk(accompanyId, tagNm)))
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

    val tags : List<String>?

){
    fun toTagEntity(accompanyId : Long) : List<AccompanyTagEntity>? {

        if(tags == null){
            return null
        }

        val tagList = mutableListOf<AccompanyTagEntity>()
        for(tagNm in tags){
            tagList.add(AccompanyTagEntity(AccompanyTagPk(accompanyId, tagNm)))
        }

        return tagList
    }
}

data class ReadAccompanyDTO(

    val accompanyId : Long,
    val userId: Long,
    val title: String,
    val content: String,
    val continent: String,
    val country : String? = null,
    val city : String? = null,
    val startTripDate: LocalDate,
    val endTripDate: LocalDate,
    val bannerImageUrl: String? = null,
    val accompanyCnt : Long,
    val viewCnt : Long,
    val likeCnt : Long,
    val tags : List<String>? = null

){
    companion object {
        @JvmStatic
        fun toDto(accompanyEntity : AccompanyEntity, accompanyDestinationEntity: AccompanyDestinationEntity, accompanyDetailEntity: AccompanyDetailEntity, tagList : List<String>?) : ReadAccompanyDTO {
            return ReadAccompanyDTO(
                accompanyId = accompanyEntity.accompanyId,
                userId = accompanyEntity.userId,
                title = accompanyEntity.title,
                content = accompanyEntity.content,
                continent = accompanyDestinationEntity.continent,
                country = accompanyDestinationEntity.country,
                city = accompanyDestinationEntity.city,
                startTripDate = accompanyEntity.startTripDate,
                endTripDate = accompanyEntity.endTripDate,
                bannerImageUrl = accompanyEntity.bannerImageUrl,
                accompanyCnt = accompanyEntity.accompanyCnt,
                viewCnt = accompanyDetailEntity.viewCnt,
                likeCnt = accompanyDetailEntity.likeCnt,
                tags = tagList
            )
        }
    }
}

@Setter
@Getter
class SearchAccompanyDTO(

    @NotNull
    val userId: Long,

    @NotBlank(message = "제목은 필수 값 입니다.")
    val title: String,

    @NotBlank(message = "내용은 필수 값 입니다.")
    val content: String,

    @NotBlank(message = "대륙은 필수 값 입니다.")
    val continent: String,

    private val country : String? = null,

    private val city : String? = null,

    @NotBlank
    val startTripDate: LocalDate,

    @NotBlank
    val endTripDate: LocalDate,

    val bannerImageUrl: String? = null,

    @NotNull
    val accompanyCnt : Long
){

}