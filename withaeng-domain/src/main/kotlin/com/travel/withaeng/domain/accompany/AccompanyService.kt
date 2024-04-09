package com.travel.withaeng.domain.accompany

import com.travel.withaeng.common.exception.InvalidAccessException
import com.travel.withaeng.common.exception.NotExistsException
import com.travel.withaeng.domain.accompany.QAccompanyEntity.accompanyEntity
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
    fun createAccompany(param : CreateAccompanyDTO) : GetDTO {
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

        return getOne(accompanyEntity.accompanyId)
    }

    @Transactional
    fun modifyAccompany(param : ModifyAccompanyDTO) : GetDTO {

        val accompanyEntity = accompanyRepository.findByAccompanyId(param.accompanyId)

        if(accompanyEntity != null){

            if(accompanyEntity.userId != param.userId){
                throw InvalidAccessException("등록자와 수정자가 달라 수정 요청을 거부 합니다.")
            }

            val accompanyHistEntity = param.toHistEntity(accompanyEntity)
            val accompanyDestinationEntity = accompanyDestinationRepository.findByAccompanyId(param.accompanyId)
            val accompanyDetailEntity = accompanyDetailRepository.findByAccompanyId(param.accompanyId)
            val accompanyTagEntityList = param.toTagEntity(accompanyEntity.accompanyId)

            accompanyEntity.let {
                it.title = param.title
                it.content = param.content
                it.startTripDate = param.startTripDate
                it.endTripDate = param.endTripDate
                it.bannerImageUrl = param.bannerImageUrl
                it.accompanyCnt = param.accompanyCnt
            }

            accompanyDetailEntity.let {
                it.openKakaoUrl = param.openKakaoUrl
            }

            accompanyDestinationEntity.let {
                it.continent = param.continent
                it.country = param.country
                it.city = param.city
            }

            accompanyHistRepository.save(accompanyHistEntity)
            accompanyTagRepository.deleteByAccompanyId(accompanyEntity.accompanyId)
            if(accompanyTagEntityList != null){
                accompanyTagRepository.saveAll(accompanyTagEntityList)
            }

        }

        return getOne(param.accompanyId)
    }

    fun getOne(param : Long) : GetDTO {

        val getAccompany = accompanyRepository.getAccompany(param)

        if(getAccompany != null){
            val tagList:List<String>? = accompanyTagRepository.findByAccompanyId(param)?.map(AccompanyTagEntity::tagNm)
            getAccompany.tags = tagList
            return getAccompany
        }

        throw NotExistsException("존재하지 않는 동행 게시글 조회 요청 입니다.")
    }

    fun getList(param : SearchAccompanyDTO) : List<GetDTO> {
       return accompanyRepository.getAccompanyList(param)
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