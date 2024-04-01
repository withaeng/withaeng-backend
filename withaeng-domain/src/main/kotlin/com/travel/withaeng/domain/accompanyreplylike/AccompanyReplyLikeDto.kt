package com.travel.withaeng.domain.accompanyreplylike

import com.travel.withaeng.common.cd.ExecCd
import com.travel.withaeng.domain.accompanylike.AccompanyLikeEntity
import com.travel.withaeng.domain.accompanylike.AccompanyLikeHistEntity
import jakarta.persistence.Column
import lombok.Getter
import lombok.Setter
import org.jetbrains.annotations.NotNull

class AccompanyReplyLikeDto {
}

@Setter
@Getter
class CreateAccompanyReplyLikeDTO(

        @NotNull
        val replyId: Long,

        @NotNull
        val userId: Long,

        ){
    fun toEntity(): AccompanyReplyLikeEntity {
        return AccompanyReplyLikeEntity(
                0,
                this.replyId,
                this.userId
        )
    }

    fun toHistEntity(entity: AccompanyReplyLikeEntity): AccompanyReplyLikeHistEntity {
        return AccompanyReplyLikeHistEntity(
                0,
                entity.replyLikeId,
                entity.replyId,
                entity.userId,
                ExecCd.CREATE.execCd
        )
    }

}

@Setter
@Getter
class DeleteAccompanyReplyLikeDTO(

        @NotNull
        val replyLikeId: Long,

        @NotNull
        val replyId: Long,

        @NotNull
        val userId: Long

        ){
    fun toEntity(): AccompanyReplyLikeEntity {
        return AccompanyReplyLikeEntity(
                this.replyLikeId,
                this.replyId,
                this.userId
        )
    }

    fun toHistEntity(entity: AccompanyReplyLikeEntity): AccompanyReplyLikeHistEntity {
        return AccompanyReplyLikeHistEntity(
                0,
                entity.replyLikeId,
                entity.replyId,
                entity.userId,
                ExecCd.DELETE.execCd
        )
    }

}