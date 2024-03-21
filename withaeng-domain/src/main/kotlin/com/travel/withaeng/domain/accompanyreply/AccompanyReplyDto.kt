package com.travel.withaeng.domain.accompanyreply

import jakarta.validation.constraints.NotBlank
import lombok.Getter
import lombok.Setter
import org.jetbrains.annotations.NotNull

class AccompanyReplyDto {

    @Setter
    @Getter
    inner class ReqAdd(

        @NotNull
        val accompanyId : Long,

        @NotNull
        val parentId : Long,

        @NotNull
        val depth : Long,

        @NotNull
        val userId: Long,

        @NotBlank(message = "내용은 필수 값 입니다.")
        val content: String,

    ) {
        fun createEntity(): AccompanyReplyEntity {
            return AccompanyReplyEntity(
                0,
                this.accompanyId,
                this.parentId,
                this.depth,
                this.userId,
                this.content
            )
        }
    }

}