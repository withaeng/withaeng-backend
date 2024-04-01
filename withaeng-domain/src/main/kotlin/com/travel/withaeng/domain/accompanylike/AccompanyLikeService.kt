package com.travel.withaeng.domain.accompanylike

import com.travel.withaeng.domain.accompany.AccompanyDetailRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class AccompanyLikeService(

    private val accompanyLikeRepository: AccompanyLikeRepository,
    private val accompanyLikeHistRepository : AccompanyLikeHistRepository,
    private val accompanyDetailRepository: AccompanyDetailRepository

) {

    @Transactional
    fun createAccompanyLike(param : CreateAccompanyLikeDTO) : CreateAccompanyLikeDTO {

        val accompanyLikeEntity = param.toEntity()
        accompanyLikeRepository.save(accompanyLikeEntity)

        val accompanyLikeHistEntity = param.toHistEntity(accompanyLikeEntity)
        accompanyLikeHistRepository.save(accompanyLikeHistEntity)

        val accompanyDetailEntity = accompanyDetailRepository.findByAccompanyId(param.accompanyId)
        accompanyDetailEntity.let {
            it.likeCnt++
        }

        return param
    }

    //TODO delete 성능이 안나올 경우 boolean 형으로 좋아요 제어
    @Transactional
    fun deleteAccompanyLike(param : DeleteAccompanyLikeDTO) : DeleteAccompanyLikeDTO{

        val accompanyLikeEntity = param.toEntity()
        accompanyLikeRepository.delete(accompanyLikeEntity)

        val accompanyLikeHistEntity = param.toHistEntity(accompanyLikeEntity)
        accompanyLikeHistEntity.deletedAt = LocalDateTime.now()
        accompanyLikeHistRepository.save(accompanyLikeHistEntity)

        return param
    }

}