package com.travel.withaeng.domain.accompanyreply

import com.travel.withaeng.common.cd.ExecCd
import com.travel.withaeng.domain.accompany.AccompanyDestinationEntity
import com.travel.withaeng.domain.accompany.AccompanyDetailEntity
import com.travel.withaeng.domain.accompany.AccompanyEntity
import com.travel.withaeng.domain.accompanylike.AccompanyLikeEntity
import com.travel.withaeng.domain.accompanylike.AccompanyLikeHistEntity
import jakarta.persistence.Column
import jakarta.validation.constraints.NotBlank
import lombok.Getter
import lombok.Setter
import org.jetbrains.annotations.NotNull
import java.time.LocalDate

class AccompanyReplyDto {
}

@Setter
@Getter
class CreateAccompanyReplyDTO(

    @NotNull
    val userId: Long,

    @NotNull
    val accompanyId: Long,

    @NotNull
    val parentId : Long,

    @NotNull
    val depth : Long,

    @NotBlank(message = "댓글 내용이 존재하지 않습니다.")
    var content: String,

    ){
    fun toEntity(): AccompanyReplyEntity {
        return AccompanyReplyEntity(
                0,
                this.accompanyId,
                this.parentId,
                this.depth,
                this.userId,
                this.content
        )
    }

    fun toHistEntity(entity: AccompanyReplyEntity): AccompanyReplyHistEntity {
        return AccompanyReplyHistEntity(
                0,
                entity.replyId,
                entity.accompanyId,
                entity.parentId,
                entity.depth,
                entity.userId,
                entity.content,
                ExecCd.CREATE.execCd
        )
    }

}

@Setter
@Getter
class ModifyAccompanyReplyDTO(

        @NotNull
        val replyId : Long,

        @NotNull
        val userId: Long,

        @NotNull
        val accompanyId: Long,

        @NotNull
        val parentId : Long,

        @NotNull
        val depth : Long,

        @NotBlank(message = "댓글 내용이 존재하지 않습니다.")
        var content: String,

        ){
    fun toEntity(): AccompanyReplyEntity {
        return AccompanyReplyEntity(
                0,
                this.accompanyId,
                this.parentId,
                this.depth,
                this.userId,
                this.content
        )
    }

    fun toHistEntity(entity: AccompanyReplyEntity): AccompanyReplyHistEntity {
        return AccompanyReplyHistEntity(
                0,
                entity.replyId,
                entity.accompanyId,
                entity.parentId,
                entity.depth,
                entity.userId,
                entity.content,
                ExecCd.UPDATE.execCd
        )
    }

}

@Setter
@Getter
class DeleteAccompanyReplyDTO(

        @NotNull
        val replyId : Long,

        @NotNull
        val userId: Long,

        @NotNull
        val accompanyId: Long

        ){

    fun toHistEntity(entity: AccompanyReplyEntity): AccompanyReplyHistEntity {
        return AccompanyReplyHistEntity(
                0,
                entity.replyId,
                entity.accompanyId,
                entity.parentId,
                entity.depth,
                entity.userId,
                entity.content,
                ExecCd.DELETE.execCd
        )
    }

}

data class ReadAccompanyReplyDTO(

        val replyId : Long,
        val userId: Long,
        val accompanyId: Long,
        val parentId : Long,
        val depth : Long,
        var content: String,

){
    companion object {
        @JvmStatic
        fun toDto(accompanyReplyEntity : AccompanyReplyEntity) : ReadAccompanyReplyDTO {
            return ReadAccompanyReplyDTO(
                    replyId = accompanyReplyEntity.replyId,
                    userId = accompanyReplyEntity.userId,
                    accompanyId = accompanyReplyEntity.accompanyId,
                    parentId = accompanyReplyEntity.parentId,
                    depth = accompanyReplyEntity.depth,
                    content = accompanyReplyEntity.content,

            )
        }
    }
}

