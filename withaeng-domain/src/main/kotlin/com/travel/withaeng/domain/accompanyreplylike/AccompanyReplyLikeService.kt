package com.travel.withaeng.domain.accompanyreplylike

import com.travel.withaeng.domain.accompany.AccompanyDetailRepository
import com.travel.withaeng.domain.accompanylike.AccompanyLikeHistRepository
import com.travel.withaeng.domain.accompanylike.AccompanyLikeRepository
import com.travel.withaeng.domain.accompanylike.CreateAccompanyLikeDTO
import com.travel.withaeng.domain.accompanylike.DeleteAccompanyLikeDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class AccompanyReplyLikeService (

        private val accompanyReplyLikeRepository: AccompanyReplyLikeRepository,
        private val accompanyReplyLikeHistRepository : AccompanyReplyLikeHistRepository

) {

    @Transactional
    fun createAccompanyReplyLike(param : CreateAccompanyReplyLikeDTO) : CreateAccompanyReplyLikeDTO {

        val accompanyReplyLikeEntity = param.toEntity()
        accompanyReplyLikeRepository.save(accompanyReplyLikeEntity)

        val accompanyReplyLikeHistEntity = param.toHistEntity(accompanyReplyLikeEntity)
        accompanyReplyLikeHistRepository.save(accompanyReplyLikeHistEntity)

        return param
    }

    //TODO delete 성능이 안나올 경우 boolean 형으로 좋아요 제어
    @Transactional
    fun deleteAccompanyReplyLike(param : DeleteAccompanyReplyLikeDTO) : DeleteAccompanyReplyLikeDTO{

        val accompanyReplyLikeEntity = param.toEntity()
        accompanyReplyLikeRepository.delete(accompanyReplyLikeEntity)

        val accompanyReplyLikeHistEntity = param.toHistEntity(accompanyReplyLikeEntity)
        accompanyReplyLikeHistEntity.deletedAt = LocalDateTime.now()
        accompanyReplyLikeHistRepository.save(accompanyReplyLikeHistEntity)

        return param
    }

    fun getAccompanyReplyLikeCnt(param: Long) : Long{
        return accompanyReplyLikeRepository.countByReplyId(param)
    }

    fun getAccompanyReplyLikeList(param : List<Long>) : List<GetReplyLikeDTO> {
        return accompanyReplyLikeRepository.getAccompanyReplyLikeList(param)
    }
}