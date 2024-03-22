package com.travel.withaeng.domain.accompanyreply

import com.travel.withaeng.domain.accompanylike.AccompanyLikeEntity
import com.travel.withaeng.domain.accompanylike.AccompanyLikeHistEntity
import jakarta.validation.constraints.NotBlank
import lombok.Getter
import lombok.Setter
import org.jetbrains.annotations.NotNull

class AccompanyReplyDto {
}

@Setter
@Getter
class CreateAccompanyReplyDTO(

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
            entity.accompanyId
        )
    }

}