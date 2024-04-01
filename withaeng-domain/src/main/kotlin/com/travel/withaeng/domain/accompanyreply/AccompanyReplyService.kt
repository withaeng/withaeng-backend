package com.travel.withaeng.domain.accompanyreply

import com.travel.withaeng.common.exception.InvalidAccessException
import com.travel.withaeng.common.exception.NotExistsException
import com.travel.withaeng.domain.accompany.*
import jakarta.persistence.Column
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccompanyReplyService (

        private val accompanyReplyRepository: AccompanyReplyRepository,
        private val accompanyReplyHistRepository : AccompanyReplyHistRepository

) {

    @Transactional
    fun createAccompanyReply(param : CreateAccompanyReplyDTO) : ReadAccompanyReplyDTO {
        val accompanyReplyEntity = param.toEntity()
        accompanyReplyRepository.save(accompanyReplyEntity)

        val accompanyReplyHistEntity = param.toHistEntity(accompanyReplyEntity)

        accompanyReplyHistRepository.save(accompanyReplyHistEntity)

        return getOne(accompanyReplyEntity.replyId)
    }

    @Transactional
    fun modifyAccompanyReply(param : ModifyAccompanyReplyDTO) : ReadAccompanyReplyDTO {

        val accompanyReplyEntity = accompanyReplyRepository.findByReplyId(param.replyId)

        if(accompanyReplyEntity != null){

            if(accompanyReplyEntity.userId != param.userId){
                throw InvalidAccessException("등록자와 수정자가 달라 수정 요청을 거부 합니다.")
            }

            accompanyReplyEntity.let {
                it.content = param.content
            }

            val accompanyReplyHistEntity = param.toHistEntity(accompanyReplyEntity)
            accompanyReplyHistRepository.save(accompanyReplyHistEntity)
        }

        return getOne(param.replyId)
    }

    @Transactional
    fun deleteAccompanyReply(param : DeleteAccompanyReplyDTO) : ReadAccompanyReplyDTO {

        val accompanyReplyEntity = accompanyReplyRepository.findByReplyId(param.replyId)

        if(accompanyReplyEntity != null){

            if(accompanyReplyEntity.userId != param.userId){
                throw InvalidAccessException("등록자와 수정자가 달라 삭제 요청을 거부 합니다.")
            }

            accompanyReplyRepository.delete(accompanyReplyEntity)

            val accompanyReplyHistEntity = param.toHistEntity(accompanyReplyEntity)
            accompanyReplyHistRepository.save(accompanyReplyHistEntity)
        }

        return getOne(param.accompanyId)
    }

    fun getOne(param : Long) : ReadAccompanyReplyDTO {

        val accompanyReplyEntity = accompanyReplyRepository.findByReplyId(param)

        if(accompanyReplyEntity != null){
            return ReadAccompanyReplyDTO.toDto(accompanyReplyEntity)
        }

        throw NotExistsException("존재하지 않는 동행 게시글 댓글 요청 입니다.")
    }

    fun getList(param : Long) : List<ReadAccompanyReplyDTO> {

        val accompanyReplyList = accompanyReplyRepository.findByAccompanyId(param)

        if(accompanyReplyList != null){

            val resultList: List<ReadAccompanyReplyDTO> = accompanyReplyList.map {
                accompanyReply -> ReadAccompanyReplyDTO.toDto(accompanyReply)
            }

            return resultList
        }

        throw NotExistsException("존재하지 않는 동행 게시글 댓글 요청 입니다.")
    }

}