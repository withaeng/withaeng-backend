package com.travel.withaeng.domain.accompany

import com.travel.withaeng.common.cd.AccompanyStatusCd
import jakarta.validation.constraints.NotBlank
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.jetbrains.annotations.NotNull
import java.time.LocalDate
import java.time.LocalDateTime

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

    val country : String,

    val city : String,

    @NotBlank
    val startTripDate: LocalDate,

    @NotBlank
    val endTripDate: LocalDate,

    val bannerImageUrl: String?,

    @NotNull
    val accompanyCnt : Long

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

    fun toCategoryEntity(accompanyId : Long) : AccompanyCategoryEntity {
        return AccompanyCategoryEntity(
                0,
                accompanyId,
                this.content,
                this.country,
                this.city
            )
    }
}