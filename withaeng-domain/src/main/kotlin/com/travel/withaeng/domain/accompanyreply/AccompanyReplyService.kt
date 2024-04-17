package com.travel.withaeng.domain.accompanyreply

import com.travel.withaeng.common.exception.InvalidAccessException
import com.travel.withaeng.common.exception.NotExistsException
import com.travel.withaeng.domain.accompany.AccompanyTagEntity
import com.travel.withaeng.domain.accompanyreplylike.AccompanyReplyLikeService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccompanyReplyService (

        private val accompanyReplyRepository: AccompanyReplyRepository,
        private val accompanyReplyHistRepository : AccompanyReplyHistRepository,
        private val accompanyReplyLikeService: AccompanyReplyLikeService

) {

    @Transactional
    fun createAccompanyReply(param : CreateAccompanyReplyDTO) : GetReplyDTO {
        val accompanyReplyEntity = param.toEntity()
        accompanyReplyRepository.save(accompanyReplyEntity)

        val accompanyReplyHistEntity = param.toHistEntity(accompanyReplyEntity)

        accompanyReplyHistRepository.save(accompanyReplyHistEntity)

        return getOne(accompanyReplyEntity.replyId)
    }

    @Transactional
    fun modifyAccompanyReply(param : ModifyAccompanyReplyDTO) : GetReplyDTO {

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
    fun deleteAccompanyReply(param : DeleteAccompanyReplyDTO) : DeleteAccompanyReplyDTO {

        val accompanyReplyEntity = accompanyReplyRepository.findByReplyId(param.replyId)

        if(accompanyReplyEntity != null){

            if(accompanyReplyEntity.userId != param.userId){
                throw InvalidAccessException("등록자와 수정자가 달라 삭제 요청을 거부 합니다.")
            }

            accompanyReplyRepository.delete(accompanyReplyEntity)

            val accompanyReplyHistEntity = param.toHistEntity(accompanyReplyEntity)
            accompanyReplyHistRepository.save(accompanyReplyHistEntity)
        }

        return param
    }

    fun getOne(param : Long) : GetReplyDTO {

        val accompanyReplyEntity = accompanyReplyRepository.findByReplyId(param)
        val accompanyReplyLikeCnt = accompanyReplyLikeService.getAccompanyReplyLikeCnt(param)

        if(accompanyReplyEntity != null){
            return GetReplyDTO.toDto(accompanyReplyEntity, accompanyReplyLikeCnt)
        }

        throw NotExistsException("존재하지 않는 동행 게시글 댓글 요청 입니다.")
    }

    //TODO 댓글당 좋아요 누른 userId 목록을 추가로 조회하여 리턴은 추후 처리
    fun getList(param : Long) : List<GetReplyDTO>? {

        val accompanyReplyList = accompanyReplyRepository.getAccompanyReplyList(param)

        if(accompanyReplyList != null){

            val replyIdList : List<Long> = accompanyReplyList.map {accompanyReplyEntity -> accompanyReplyEntity.replyId}.toList()
            val accompanyReplyLikeList = accompanyReplyLikeService.getAccompanyReplyLikeList(replyIdList)

            for(reply in accompanyReplyList){
                for(like in accompanyReplyLikeList){
                    if(reply.replyId == like.replyId){
                        reply.likeCnt = like.likeCnt
                    }
                }
            }

            val parentList = accompanyReplyList.stream().filter{
                e-> e.parentId.equals(0L)
            }.toList()

            val sortList = accompanyReplyList.sortedWith(compareBy({it.parentId},{it.depth}, {it.replyOrder})).toList()
            val resultList = mutableListOf<GetReplyDTO>()

            parentList.stream().forEach{
                e->
                resultList.add(e)
                sortList.forEach{a->
                    if(e.replyId.equals(a.parentId)){
                        resultList.add(a)
                    }
                }
            }

            return resultList
        }

        return null
    }

}