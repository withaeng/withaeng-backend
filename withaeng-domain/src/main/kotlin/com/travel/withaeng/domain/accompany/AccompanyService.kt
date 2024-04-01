package com.travel.withaeng.domain.accompany

import com.travel.withaeng.common.exception.InvalidAccessException
import com.travel.withaeng.common.exception.NotExistsException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccompanyService(

    private val accompanyRepository: AccompanyRepository,
    private val accompanyHistRepository : AccompanyHistRepository,
    private val accompanyDestinationRepository : AccompanyDestinationRepository,
    private val accompanyDetailRepository : AccompanyDetailRepository,
    private val accompanyTagRepository: AccompanyTagRepository

) {

    @Transactional
    fun createAccompany(param : CreateAccompanyDTO) : ReadAccompanyDTO {
        val accompanyEntity = param.toEntity()
        accompanyRepository.save(accompanyEntity)

        val accompanyHistEntity = param.toHistEntity(accompanyEntity)
        val accompanyDestinationEntity = param.toDestinationEntity(accompanyEntity.accompanyId)
        val accompanyDetailEntity = param.toDetailEntity(accompanyEntity.accompanyId)
        val accompanyTagEntityList = param.toTagEntity(accompanyEntity.accompanyId)

        accompanyHistRepository.save(accompanyHistEntity)
        accompanyDestinationRepository.save(accompanyDestinationEntity)
        accompanyDetailRepository.save(accompanyDetailEntity)

        if(accompanyTagEntityList != null){
            accompanyTagRepository.saveAll(accompanyTagEntityList)
        }

        return getOne(accompanyHistEntity.accompanyId)
    }

    @Transactional
    fun modifyAccompany(param : ModifyAccompanyDTO) : ReadAccompanyDTO {

        val accompanyEntity = accompanyRepository.findByAccompanyId(param.accompanyId)

        if(accompanyEntity != null){

            if(accompanyEntity.userId != param.userId){
                throw InvalidAccessException("등록자와 수정자가 달라 수정 요청을 거부 합니다.")
            }

            val accompanyDestinationEntity = accompanyDestinationRepository.findByAccompanyId(param.accompanyId)
            val accompanyTagEntityList = param.toTagEntity(accompanyEntity.accompanyId)

            accompanyEntity.let {
                it.title = param.title
                it.content = param.content
                it.startTripDate = param.startTripDate
                it.endTripDate = param.endTripDate
                it.bannerImageUrl = param.bannerImageUrl
                it.accompanyCnt = param.accompanyCnt
            }

            accompanyDestinationEntity.let {
                it.continent = param.continent
                it.country = param.country
                it.city = param.city
            }

            accompanyTagRepository.deleteByAccompanyId(accompanyEntity.accompanyId)
            if(accompanyTagEntityList != null){
                accompanyTagRepository.saveAll(accompanyTagEntityList)
            }

        }

        return getOne(param.accompanyId)
    }

    fun getOne(param : Long) : ReadAccompanyDTO {
        val accompanyEntity = accompanyRepository.findByAccompanyId(param)

        if(accompanyEntity != null){
            val accompanyDestinationEntity = accompanyDestinationRepository.findByAccompanyId(param)
            val accompanyDetailEntity = accompanyDetailRepository.findByAccompanyId(param)
            val tagList:List<String>? = accompanyTagRepository.findByAccompanyId(param)?.map(AccompanyTagEntity::tagNm)
            return ReadAccompanyDTO.toDto(accompanyEntity, accompanyDestinationEntity, accompanyDetailEntity, tagList)
        }

        throw NotExistsException("존재하지 않는 동행 게시글 조회 요청 입니다.")
    }

    fun getList(param : SearchAccompanyDTO) : List<ReadAccompanyDTO> {
        /*val accompanyList = accompanyRepository.findAll(param)
        if(accompanyEntity != null){
            val accompanyDestinationEntity = accompanyDestinationRepository.findByAccompanyId(param)
            val accompanyDetailEntity = accompanyDetailRepository.findByAccompanyId(param)
            return ReadAccompanyDTO.toDto(accompanyEntity, accompanyDestinationEntity)
        }*/
        return ArrayList()
    }

    @Transactional
    fun incrViewCnt(param : Long) {
        val accompanyDetailEntity = accompanyDetailRepository.findByAccompanyId(param)
        accompanyDetailEntity.let {
            it.viewCnt++
        }
    }

    @Transactional
    fun decrViewCnt(param : Long){
        val accompanyDetailEntity = accompanyDetailRepository.findByAccompanyId(param)
        accompanyDetailEntity.let {
            it.viewCnt--
        }
    }
}