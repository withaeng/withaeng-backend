package com.travel.withaeng.domain.accompanylike

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class AccompanyLikeService(

    private val accompanyLikeRepository: AccompanyLikeRepository,
    private val accompanyLikeHistRepository : AccompanyLikeHistRepository

) {

    @Transactional
    fun createAccompanyLike(param : CreateAccompanyLikeDTO) : Long {

        val accompanyLikeEntity = param.toEntity()
        accompanyLikeRepository.save(accompanyLikeEntity)

        val accompanyLikeHistEntity = param.toHistEntity(accompanyLikeEntity)
        accompanyLikeHistRepository.save(accompanyLikeHistEntity)

        return accompanyLikeEntity.likeId
    }

    //TODO delete 성능이 안나올 경우 boolean 형으로 좋아요 제어
    @Transactional
    fun deleteAccompanyLike(param : DeleteAccompanyLikeDTO){

        val accompanyLikeEntity = param.toEntity()
        accompanyLikeRepository.delete(accompanyLikeEntity)

        val accompanyLikeHistEntity = param.toHistEntity(accompanyLikeEntity)
        accompanyLikeHistEntity.deletedAt = LocalDateTime.now()
        accompanyLikeHistRepository.save(accompanyLikeHistEntity)

    }

}