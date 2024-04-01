package com.travel.withaeng.domain.accompanylike

import com.travel.withaeng.common.cd.AccompanyStatusCd
import com.travel.withaeng.common.cd.ExecCd
import com.travel.withaeng.domain.accompany.AccompanyDestinationEntity
import com.travel.withaeng.domain.accompany.AccompanyEntity
import com.travel.withaeng.domain.accompany.AccompanyHistEntity
import jakarta.validation.constraints.NotBlank
import lombok.Getter
import lombok.Setter
import org.jetbrains.annotations.NotNull
import java.time.LocalDate

class AccompanyLikeDto {
}

@Setter
@Getter
class CreateAccompanyLikeDTO(

    @NotNull
    val userId: Long,

    @NotNull
    val accompanyId: Long,

){
    fun toEntity(): AccompanyLikeEntity {
        return AccompanyLikeEntity(
            0,
            this.userId,
            this.accompanyId
        )
    }

    fun toHistEntity(entity: AccompanyLikeEntity): AccompanyLikeHistEntity {
        return AccompanyLikeHistEntity(
            0,
            entity.likeId,
            entity.userId,
            entity.accompanyId,
            ExecCd.CREATE.execCd
        )
    }

}

@Setter
@Getter
class DeleteAccompanyLikeDTO(

    @NotNull
    val likeId : Long,

    @NotNull
    val userId: Long,

    @NotNull
    val accompanyId: Long,

    ){
    fun toEntity(): AccompanyLikeEntity {
        return AccompanyLikeEntity(
            this.likeId,
            this.userId,
            this.accompanyId
        )
    }

    fun toHistEntity(entity: AccompanyLikeEntity): AccompanyLikeHistEntity {
        return AccompanyLikeHistEntity(
            0,
            entity.likeId,
            entity.userId,
            entity.accompanyId,
            ExecCd.DELETE.execCd
        )
    }

}